package com.lucafaggion

import com.lucafaggion.auth.*
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import grails.buildtestdata.TestDataBuilder
import org.springframework.beans.factory.annotation.Autowired
import grails.plugin.springsecurity.SpringSecurityUtils

@Integration
@Rollback
class UserLogicServiceSpec extends Specification implements TestDataBuilder{

    @Autowired
    UserLogicService service

    def setupData() {
        def user = User.get(1)
        Orders.build(state:'cart',user:user,paymentDetails:PaymentInfo.build(user:user),shippingDetails:ShippingInfo.build(user:user))
        def cart = Orders.build(state:'cart',user:user,paymentDetails:PaymentInfo.build(user:user),shippingDetails:ShippingInfo.build(user:user))
        for (int i = 0; i < 3; i++) {
            Product.build(price:2,quantity:20)
        }
        for (int i = 0; i < 3; i++) {
            LineItem.build(orderid:cart,price:5,subProduct:Product.list()[i],quantity:2)
        }
    }

    void "Test create user role with admin privileges"() {
        when:
        SpringSecurityUtils.doWithAuth('lucafaggion', {
            service.createUserRole(User.build(), Role.build())
        })

        then:"The UserRole table get updated"
        UserRole.list().size() == 4
    }

    void "Test create user role with user privileges"() {
        when:
            SpringSecurityUtils.doWithAuth('mariorossi', {
                service.createUserRole(User.build(), null)
            })

        then:"The UserRole table get updated with a ROLE_USER by default"
        UserRole.list().size() == 4
        UserRole.list()[3].role.authority == "ROLE_USER"
    }

    void "Test GetUserDatabyId with valid user"(){
        when:
        def data = service.getUserDatabyId(1)

        then:"The return value is not null"
        data != null
    }

    void "Test GetUserDatabyId with an invalid user"(){
        when:
        def data = service.getUserDatabyId(null)

        then:"The return value is null"
        !data
    }

    void "Test GetUserDatabyUser with valid user"(){
        when:
        def data = service.getUserDatabyUser(User.get(1))

        then:"The return value is not null"
        data != null
    }

    void "Test GetUserDatabyUser with an invalid user"(){
        when:
        def data = service.getUserDatabyUser(null)

        then:"The return value is null"
        !data
    }

    void "Test getUserRolesById with valid user"(){
        when:
        def data = service.getUserRolesById(1)

        then:"The return value is not null"
        data != null
    }

    void "Test getUserRolesById with an invalid user"(){
        when:
        def data = service.getUserRolesById(null)

        then:"The return value is null"
        !data
    }

    void "Test deleteUserOrdersById with a valid user"(){
        given:
        service.orderslogicService = Mock(OrderslogicService) {
            2 * deleteOrder(_) >> { Long id -> Orders.get(id).delete(flush:true) }
        }
        setupData()

        when:
        service.deleteUserOrdersById(User.get(1).id)

        then:"The Orders get updated"
        Orders.list().size() == 0
    }

    void "Test deleteUserOrdersById with an invalid user"(){
        when:
        setupData()
        service.deleteUserOrdersById(null)

        then:"No Orders get Updated"
        Orders.list().size() == 2
    }

    void "Test deleteUserDetailsById with a valid user"(){
        when:
        service.deleteUserDetailsById(User.get(1).id)

        then:"Domain ShippingInfo and PaymentInfo get updated"
        ShippingInfo.list().size() == 2
        PaymentInfo.list().size() == 2
    }

    void "Test deleteUserDetailsById with an invalid user"(){
        when:
        service.deleteUserDetailsById(null)

        then:"No changes"
        ShippingInfo.list().size() == 3
        PaymentInfo.list().size() == 3
    }

}
