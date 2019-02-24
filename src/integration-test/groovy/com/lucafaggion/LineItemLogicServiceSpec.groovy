package com.lucafaggion

import com.lucafaggion.auth.User
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import grails.buildtestdata.TestDataBuilder
import org.springframework.beans.factory.annotation.Autowired

@Integration
@Rollback
class LineItemLogicServiceSpec extends Specification implements TestDataBuilder{

    @Autowired
    LineItemLogicService service

    def setupData() {
        def user = User.get(1)
        def order1 = Orders.build(user:user,paymentDetails:PaymentInfo.build(user:user),shippingDetails:ShippingInfo.build(user:user))
        def order2 = Orders.build(user:user,paymentDetails:PaymentInfo.build(user:user),shippingDetails:ShippingInfo.build(user:user))
        LineItem.build(orderid:order1)
        LineItem.build(orderid:order1)
        LineItem.build(orderid:order2)
    }


    void "Test getAllLineItemsOfOrder return correct data"() {
        setupData()
        expect:"Return array of count == 2"
            service.getAllLineItemsOfOrder(Orders.list()[0]).size() == 2
    }

    void "Test getAllLineItemsOfOrderById return correct data"() {
        setupData()
        expect:"Return array of count == 1"
            service.getAllLineItemsOfOrderById(Orders.list()[1].id).size() == 1
    }

    void "Test getAllLineItemsOfOrder return correct data with params"() {
        setupData()
        expect:"Return array of count == 1"
            service.getAllLineItemsOfOrder(Orders.list()[0],[order:'asc',sort:'id']).size() == 2
    }
}
