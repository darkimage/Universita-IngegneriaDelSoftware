package com.lucafaggion.auth

import com.lucafaggion.*
import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import spock.lang.*

class UserControllerSpec extends Specification implements ControllerUnitTest<UserController>, DataTest {

    void setupSpec() {
        mockDomain Role
        mockDomain User
    }

    def setup() {
        new User(username: 'lucafaggion', password: '12345', name:'Luca',surname:'Faggion',birthDate: Date.parse('dd-MM-yyy',"04-12-1995"),fiscalCode:'FGGLCU95T04E463P',email:'luca.faggion@gmail.com').save()
        def roleUser = new Role(name: 'User', authority: 'ROLE_USER')
        roleUser.id = 3
        roleUser.save()
    }

    def populateValidParams(params) {
        assert params != null

        //Populate valid properties like...
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

    def populateValidParamsUpdate(params){
        assert params != null
        params.id = 1
    }

    void "Test the save action correctly persists"() {
        given:
        controller.userService = Mock(UserService) {
            1 * save(_ as User)
        }
        controller.shippingInfoService = Mock(ShippingInfoService) {
            1 * save(_ as ShippingInfo)
        }
        controller.userLogicService = Mock(UserLogicService) {
            1 * createUserRole(_ as Object,_ as Object) >> true
        }
        controller.paymentInfoService = Mock(PaymentInfoService) {
            1 * save(_ as PaymentInfo)
        }


        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def user = new User(params)
        user.id = 1

        controller.save(user)

        then:"A redirect is issued to main page"
        response.redirectedUrl == '/'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.userService = Mock(UserService) {
            1 * save(_ as User) >> { User user ->
                throw new ValidationException("Invalid instance", user.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def user = new User()
        controller.save(user)

        then:"The create view is rendered again with the correct model"
        model.user != null
        view == 'create'
    }


    void "Test the index action returns the correct model"() {
        given:
        controller.userService = Mock(UserService) {
            1 * list(_) >> []
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.userList
        model.userCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.user!= null
    }

    void "Test the save action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/user/index'
        flash.message != null
    }

    void "Test the show action with a null id"() {
        given:
        controller.userLogicService = Mock(UserLogicService) {
            1 * getUserDatabyId(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.userLogicService = Mock(UserLogicService) {
            1 * getUserDatabyId(_ as Long) >> new User()
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.userData instanceof User
    }

    void "Test the edit action with a null id"() {
        given:
        controller.userService = Mock(UserService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.userService = Mock(UserService) {
            1 * get(2) >> new User()
        }
        controller.userLogicService = Mock(UserLogicService) {
            1 * getUserDatabyUser(new User()) >> new User()
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
        response.redirectedUrl == '/user/index'
        flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.userService = Mock(UserService) {
            1 * save(_ as User) >> { User user ->
                throw new ValidationException("Invalid instance", user.errors)
            }
        }
        controller.userLogicService = Mock(UserLogicService) {
            1 * getUserDatabyUser(_ as User) >> { User user ->
                [user:user,userShippingInfo:new ShippingInfo(),userPaymentInfo:new PaymentInfo()]
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParamsUpdate(params)
        controller.update()

        then:"The edit view is rendered again with the correct model"
        model.userData != null
        view == 'edit'

    }
}






