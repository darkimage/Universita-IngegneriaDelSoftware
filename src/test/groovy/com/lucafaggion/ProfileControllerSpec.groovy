package com.lucafaggion

import com.lucafaggion.auth.*
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import grails.buildtestdata.BuildDataTest
import spock.lang.Specification
import grails.plugin.springsecurity.SpringSecurityService

class ProfileControllerSpec extends Specification implements ControllerUnitTest<ProfileController>, BuildDataTest {

    void setupSpec() {
        mockDomain User
        mockDomain PaymentInfo
        mockDomain ShippingInfo
    }

    def setup() {
        User.build()
    }

    def populateValidParams(params) {
        assert params != null

        params.name = 'Test Nome'
        params.surname = 'Test Cognome'
        params.birthDate = new Date()
        params.fiscalCode ='TEST90TESTTT90RT'
        params.email ='test@test.test'
        params.role = 3
        params.username ='UserTest'
        params.password ='1234Test'
        params.address ='Via Test'
        params.number =1
        params.state ='Test'
        params.postalzone =2
        params.creditcard ='4136 0990 9952 4444'
        params.cvc =212
        params.circuit ='mastercard'
        params.expirationDate =new Date()
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.springSecurityService = Mock(SpringSecurityService){
            1 * getCurrentUser() >> User.get(1)
        }
        controller.userLogicService = Mock(UserLogicService) {
            1 * getUserDatabyUser(_) >> []
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.userData
        view == '/profile/index'
    }

    void "Test the edit action with a null id"() {
        given:
        controller.userLogicService = Mock(UserLogicService) {
            1 * getUserDatabyId(_) >> []
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.userLogicService = Mock(UserLogicService) {
            1 * getUserDatabyId(2) >> new User()
        }


        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.userData instanceof User
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/profile/index'
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.userService = Mock(UserService) {
            1 * save(_ as User)
        }
        controller.shippingInfoService = Mock(ShippingInfoService) {
            1 * save(_ as ShippingInfo)
        }
        controller.paymentInfoService = Mock(PaymentInfoService) {
            1 * save(_ as PaymentInfo)
        }


        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)

        controller.update(1)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/profile/index'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.userService = Mock(UserService) {
            1 * save(_ as User) >> { User user ->
                throw new ValidationException("Invalid instance", user.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(1)

        then:"The edit view is rendered again with the correct model"
        model.userData != null
        view == 'edit'
    }

}