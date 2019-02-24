package com.lucafaggion.auth
import com.lucafaggion.*
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils

@Secured(value="hasRole('ROLE_ADMIN')")
class UserController {
    def springSecurityService
    UserService userService
    ShippingInfoService shippingInfoService
    PaymentInfoService paymentInfoService
    UserLogicService userLogicService
    UtilityService utilityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
    }

    def show(Long id) {
        def userData = userLogicService.getUserDatabyId(id)
        if(userData != null) {
            respond([userData: userData])
        }else{
            notFound()
        }
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
        def userRole = Role.get(params.role)

        try {
            userService.save(user)
            shippingInfoService.save(shippingInfo)
            paymentInfoService.save(paymentInfo)
            userLogicService.createUserRole(user,userRole)
        } catch (ValidationException e) {
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
        if(userEdit != null) {
            def data = userLogicService.getUserDatabyUser(userEdit)
            respond([userData: data])
        }else{
            notFound()
        }
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
        def userPayment = new PaymentInfo(params)
        userPayment.user = user
        userPayment.version = params.int('version')
        def userShipping = new ShippingInfo(params)
        userShipping.user = user
        userShipping.version = params.int('version')
        try {
            userService.save(user)
            shippingInfoService.save(userShipping)
            paymentInfoService.save(userPayment)
        } catch (ValidationException e) {
            def data = userLogicService.getUserDatabyUser(user)
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
        userLogicService.deleteUserRolesById(id)
        userLogicService.deleteUserOrdersById(id)
        userLogicService.deleteUserDetailsById(id)
        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: 200 }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: 404 }
        }
    }
}
