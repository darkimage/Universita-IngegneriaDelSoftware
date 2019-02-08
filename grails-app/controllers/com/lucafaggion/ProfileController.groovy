package com.lucafaggion
import com.lucafaggion.auth.*
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(value=["isFullyAuthenticated()"])
class ProfileController {
    def springSecurityService
    UserService userService
    ShippingInfoService shippingInfoService
    PaymentInfoService paymentInfoService
    UserLogicService userLogicService
    UtilityService utilityService

    def index = {
        def user = springSecurityService.getCurrentUser()
        render(view:"index",model:[params:params,userData:userLogicService.getUserDatabyUser(user)])
    }

    def edit = {
        respond([userData:userLogicService.getUserDatabyUser(user)])
    }

    def update = {
        def user = User.get(params.id)
        if (user == null) {
            notFound()
            return
        }
        if(!params.password){
            bindData(user,params,[exclude:['password']])
        }else{
            bindData(user,params)
        }
        def userPayment = PaymentInfo.find("FROM PaymentInfo as p WHERE p.user=:user",[user:user])
        def userShipping = ShippingInfo.find("FROM ShippingInfo as s WHERE s.user=:user",[user:user])
        bindData(userPayment, params)
        bindData(userShipping, params)
        try {
            userService.save(user)
            shippingInfoService.save(userShipping)
            paymentInfoService.save(userPayment)   
        } catch (ValidationException e) {
            def data = [user:user,userShippingInfo:userShipping,userPaymentInfo:userPayment]
            respond([userData:data], view:'edit')
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

}
