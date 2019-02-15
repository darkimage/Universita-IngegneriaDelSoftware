package com.lucafaggion

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
        def order1 = Orders.build()
        def order2 = Orders.build()
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
