package com.lucafaggion

import com.lucafaggion.auth.*
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import grails.buildtestdata.TestDataBuilder
import org.springframework.beans.factory.annotation.Autowired
import grails.plugin.springsecurity.SpringSecurityService

@Integration
@Rollback
class OrderslogicServiceSpec extends Specification implements TestDataBuilder{

    @Autowired
    OrderslogicService service

    def setupData() {
        def cart = Orders.build(state:'cart',user:User.get(1))
        for (int i = 0; i < 3; i++) {
            Product.build(price:2)
        }
        for (int i = 0; i < 3; i++) {
            LineItem.build(orderid:cart,price:5,subProduct:Product.list()[i])
        }
    }


    void "Test retrieving shopping cart order with authenticated user"() {
        given:
        service.springSecurityService = Mock(SpringSecurityService) {
            1 * getCurrentUser() >> User.get(1)
        }
        setupData()

        expect:"Return the shopping cart order"
        def cart = service.getUserShoppingCartOrder()
        cart != null
        cart.lineItem.size() == 3
    }

    void "Test retrieving shopping cart order authenticated user but no cart order"(){
        given:
        service.springSecurityService = Mock(SpringSecurityService) {
            1 * getCurrentUser() >> User.get(1)
        }

        expect:"Return an new cart"
        def cart = service.getUserShoppingCartOrder()
        cart != null
        cart.lineItem == null
    }

    void "Test retrieving shopping cart order with no user"(){
        given:
        service.springSecurityService = Mock(SpringSecurityService) {
            1 * getCurrentUser() >> null
        }
        setupData()

        expect:"Return an empty response"
        service.getUserShoppingCartOrder() == null
    }

    void "Test retrieving shopping cart items"(){
        given:
        service.springSecurityService = Mock(SpringSecurityService) {
            1 * getCurrentUser() >> User.get(1)
        }
        setupData()

        expect:"Return the list of items"
        service.getUserShoppingCart().size == 3
    }

    void "Test add new Item to user cart"(){
        given:
        service.springSecurityService = Mock(SpringSecurityService) {
            3 * getCurrentUser() >> User.get(1)
        }
        setupData()

        expect:"Return the new added item and increased the list of items by 1"
        def item = Product.build(price:1)
        service.addToUserCart(item.id,1).id == item.id
        service.getUserShoppingCart().size == 4
    }

    void "Test add already existing Item to user cart"(){
        given:
        service.springSecurityService = Mock(SpringSecurityService) {
            3 * getCurrentUser() >> User.get(1)
        }
        setupData()

        expect:"Return the old item and same cart items size"
            service.addToUserCart(LineItem.list()[0].id,1).id == LineItem.list()[0].id
            service.getUserShoppingCart().size == 3
    }

    void "Test calculate total price"(){
        given:
        setupData()

        expect:"Return the correct price == 15"
        service.calculateTotalPrice(LineItem.list()) == 15
    }

    void "Test update a cart product"(){
        given:
        service.springSecurityService = Mock(SpringSecurityService) {
            1 * getCurrentUser() >> User.get(1)
        }
        setupData()

        expect:"Return the correct updated values"
        def cart = service.getUserShoppingCart()
        println cart[0].price
        def item = cart[0]
        service.updateCartProduct(item.id,5)
        item.quantity == 5
        item.price == 10
    }


}
