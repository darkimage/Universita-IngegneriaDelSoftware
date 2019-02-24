package com.lucafaggion

import com.lucafaggion.auth.User
import grails.testing.web.controllers.ControllerUnitTest
import grails.buildtestdata.BuildDataTest
import spock.lang.Specification
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.SpringSecurityService

class OrdersControllerSpec extends Specification implements ControllerUnitTest<OrdersController>, BuildDataTest  {

    void setupSpec() {
        mockDomain Orders
        mockDomain ShippingInfo
        mockDomain PaymentInfo
        mockDomain LineItem
        mockDomain User
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.orderslogicService = Mock(OrderslogicService) {
            1 * getOrders(_) >> []
        }
        controller.ordersService = Mock(OrdersService){
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.ordersList
        model.ordersCount == 0
    }

    void "Test the show action with a null id"() {
        given:
        controller.ordersService = Mock(OrdersService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id and valid user (admin/dipendente)"() {
        given:
        def order = Orders.build()
        controller.ordersService = Mock(OrdersService) {
            1 * get(2) >> order
        }
        controller.lineItemLogicService = Mock(LineItemLogicService){
                1 * getAllLineItemsOfOrder(_,_) >> [LineItem.build(orderid:order),LineItem.build(orderid:order)]
        }
        controller.springSecurityService = Mock(SpringSecurityService){
            1 * getCurrentUser() >> new User()
        }

        //NON E RACCOMANDATO SOVRASCRIVERE LA META CLASS IN QUESTO MODO MA NON HO TROVATO ALTRE SOLUZIONI SENZA PASSARE AD
        //UN TEST DI INTEGRAZIONE
        SpringSecurityUtils.metaClass.'static'.ifAnyGranted = { String role ->
            return true
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.orders instanceof Orders
        model.lineItems.size() == 2
    }

    void "Test the show action with a valid id and an invalid user"() {
        given:
        def order = Orders.build()
        controller.ordersService = Mock(OrdersService) {
            1 * get(2) >> order
        }
        controller.lineItemLogicService = Mock(LineItemLogicService){
            1 * getAllLineItemsOfOrder(_,_) >> [LineItem.build(orderid:order),LineItem.build(orderid:order)]
        }
        controller.springSecurityService = Mock(SpringSecurityService){
            1 * getCurrentUser() >> new User()
        }

        //NON E RACCOMANDATO SOVRASCRIVERE LA META CLASS IN QUESTO MODO MA NON HO TROVATO ALTRE SOLUZIONI SENZA PASSARE AD
        //UN TEST DI INTEGRAZIONE
        SpringSecurityUtils.metaClass.'static'.ifAnyGranted = { String role ->
            return false
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"The user is redirected to the denied page"
        view == '/login/denied'
    }

    void "Test the show action with a valid id and a valid user (user which owns the order)"() {
        given:
        def user = User.build()
        def order = Orders.build(user:user)
        controller.ordersService = Mock(OrdersService) {
            1 * get(2) >> order
        }
        controller.lineItemLogicService = Mock(LineItemLogicService){
            1 * getAllLineItemsOfOrder(_,_) >> [LineItem.build(orderid:order),LineItem.build(orderid:order)]
        }
        controller.springSecurityService = Mock(SpringSecurityService){
            1 * getCurrentUser() >> user
        }

        //NON E RACCOMANDATO SOVRASCRIVERE LA META CLASS IN QUESTO MODO MA NON HO TROVATO ALTRE SOLUZIONI SENZA PASSARE AD
        //UN TEST DI INTEGRAZIONE
        SpringSecurityUtils.metaClass.'static'.ifAnyGranted = { String role ->
            return false
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"The user is redirected to the denied page"
        model.orders instanceof Orders
        model.lineItems.size() == 2
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/orders/index'
        flash.message != null
    }

    void "Test the delete action with an instance and admin/dipendente user"() {
        given:
        controller.orderslogicService = Mock(OrderslogicService) {
            1 * deleteOrder(2)
        }

        //NON E RACCOMANDATO SOVRASCRIVERE LA META CLASS IN QUESTO MODO MA NON HO TROVATO ALTRE SOLUZIONI SENZA PASSARE AD
        //UN TEST DI INTEGRAZIONE
        SpringSecurityUtils.metaClass.'static'.ifAnyGranted = { String role ->
            return true
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/orders/index'
        flash.message != null
    }

    void "Test the delete action with an instance and a normal user (cliente)"() {
        given:
        controller.orderslogicService = Mock(OrderslogicService) {
            1 * deleteOrder(2)
        }
        //NON E RACCOMANDATO SOVRASCRIVERE LA META CLASS IN QUESTO MODO MA NON HO TROVATO ALTRE SOLUZIONI SENZA PASSARE AD
        //UN TEST DI INTEGRAZIONE
        SpringSecurityUtils.metaClass.'static'.ifAnyGranted = { String role ->
            return false
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/profile/index'
        flash.message != null
    }
}