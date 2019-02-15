package com.lucafaggion

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class ControlPanelControllerSpec extends Specification implements ControllerUnitTest<ControlPanelController> {

    void "Test redirection to user management"() {
        when:"we use the manage user button"
        controller.manageUsers()

        then:"Redirection to the user management controller action"
        response.redirectedUrl == '/user/index'
    }

    void "Test redirection to product management"() {
        when:"we use the manage product button"
        controller.manageProducts()

        then:"Redirection to the user management controller action"
        response.redirectedUrl == '/product/manage'
    }

    void "Test redirection to orders management"() {
        when:"we use the manage user button"
        controller.manageOrders()

        then:"Redirection to the user management controller action"
        response.redirectedUrl == '/orders/index'
    }

    void "Test redirection to product categories management"() {
        when:"we use the manage user button"
        controller.manageCategories()

        then:"Redirection to the user management controller action"
        response.redirectedUrl == '/productCategory/index'
    }
}
