package com.lucafaggion

import grails.testing.web.controllers.ControllerUnitTest
import grails.buildtestdata.BuildDataTest
import spock.lang.Specification

class ShoppingCartControllerSpec extends Specification implements ControllerUnitTest<ShoppingCartController>, BuildDataTest  {

    void setupSpec() {
        mockDomain Orders
        mockDomain Product
    }

    def populateValidParams(params) {
        assert params != null
        params["quantity"] = 10
        params["lastController"] = 'shoppingCart'
        params["lastAction"] = 'add'
    }

    void setup(){
        Product.build()
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.orderslogicService = Mock(OrderslogicService) {
            1 * getUserShoppingCart(_) >> []
            1 * calculateTotalPrice(_) >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        view == '/shoppingCart/index'
        !model.cartItems
        model.params
        model.itemsCount == 0
        model.totalprice == 0
    }


    void "Test the update action with a null instance"() {
        given:
        controller.lineitemService = Mock(LineitemService){
            1 * get(null) >> null
        }

        when:"Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.update(null,null)

        then:"A 404 error is returned"
        response.status == 404
    }


    void "Test the update action correctly update a product"() {
        given:
        controller.orderslogicService = Mock(OrderslogicService) {
            1 * updateCartProduct(_,_)
        }
        controller.lineitemService = Mock(LineitemService){
            1 * get(1) >> new LineItem()
        }

        when:"The update action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        controller.update(1,10)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/shoppingCart/index'
        controller.flash.message != null
    }

    void "Test the updateCart action with the placeorder parameter and an invalid instance"() {
        given:
        controller.orderslogicService = Mock(OrderslogicService) {
            1 * placeUserOrder() >> {
                throw new ControllerException(code:"com.lucafaggon.error500")
            }
        }

        when:"The updateCart action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        params.placeorder = true
        controller.updateCart()

        then:"The index view is rendered again with the correct model and an error message"
        flash.error != null
    }

    void "Test the updateCart action with the placeorder parameter and a valid instance"() {
        given:
        def order = Orders.build()
        controller.orderslogicService = Mock(OrderslogicService) {
            1 * placeUserOrder() >> order
        }

        when:"The updateCart action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        params.placeorder = true
        controller.updateCart()

        then:"The user get redirected to the order show page"
        flash.message != null
        response.redirectedUrl == '/orders/show/' + order.id
    }

    void "Test the updateCart action with the deleteorder parameter and an invalid instance"() {
        given:
        controller.orderslogicService = Mock(OrderslogicService) {
            1 * deleteShoppingCart() >> {
                throw new Exception("Invalid Instance")
            }
        }

        when:"The updateCart action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        params.deleteorder = true
        controller.updateCart()

        then:"The user get redirected index page"
        flash.error != null
        response.redirectedUrl == '/shoppingCart/index'
    }

    void "Test the updateCart action with the deleteorder parameter and a valid instance"() {
        given:
        controller.orderslogicService = Mock(OrderslogicService) {
            1 * deleteShoppingCart()
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        params.deleteorder = true
        controller.updateCart()

        then:"The user get redirected to the index page"
        flash.message != null
        response.redirectedUrl == '/shoppingCart/index'
    }
}






