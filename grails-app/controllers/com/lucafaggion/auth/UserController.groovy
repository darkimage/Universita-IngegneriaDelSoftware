package com.lucafaggion.auth
import com.lucafaggion.*
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(value="hasRole('ROLE_ADMIN')")
class UserController { 

    UserService userService
    ShippingInfoService shippingInfoService
    PaymentInfoService paymentInfoService
    UserlogicService userlogicService
    UtilityService utilityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
    }

    def show(Long id) { 
        def userPayment = PaymentInfo.find("FROM PaymentInfo as p WHERE p.user.id=:user",[user:id])
        def userShipping = ShippingInfo.find("FROM ShippingInfo as s WHERE s.user.id=:user",[user:id])
        def user = userService.get(id)
        def data = [user:user,userShippingInfo:userShipping,userPaymentInfo:userPayment]
        respond([userData:data])
    } 

    @Secured(value=["permitAll"])
    def create() {
        respond new User(params)
    }

    @Secured(value=["permitAll"])
    def save(User user) {
        if (user == null) {
            notFound()
            return
        }
        def shippingInfo = new ShippingInfo(params)
        shippingInfo.user = user
        def paymentInfo = new PaymentInfo(params)
        paymentInfo.user = user

        try {
            userService.save(user)
            shippingInfoService.save(shippingInfo)
            paymentInfoService.save(paymentInfo)
        } catch (ValidationException e) {
            println user.errors
            respond user.errors, view:'create'
            return 
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect(uri:'/')
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(Long id) {
        def userEdit = userService.get(id)
        def userPayment = PaymentInfo.find("FROM PaymentInfo as p WHERE p.user=:user",[user:userEdit])
        def userShipping = ShippingInfo.find("FROM ShippingInfo as s WHERE s.user=:user",[user:userEdit])
        def data = [user:userEdit,userShippingInfo:userShipping,userPaymentInfo:userPayment]
        respond([userData:data])
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

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        def userPayment = PaymentInfo.find("FROM PaymentInfo as p WHERE p.user.id=:user",[user:id])
        def userShipping = ShippingInfo.find("FROM ShippingInfo as s WHERE s.user.id=:user",[user:id])
        userPayment.delete()
        userShipping.delete()
        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
