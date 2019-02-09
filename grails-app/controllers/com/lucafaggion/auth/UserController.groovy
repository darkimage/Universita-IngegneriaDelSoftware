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
        respond([userData:userLogicService.getUserDatabyId(id)])
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
            if(SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')){
                UserRole.create(user,userRole,true)
            }else{
                UserRole.create(user,Role.find("FROM Role as r WHERE r.authority=:role",[role:'ROLE_USER']),true)
            }
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
        def data = userLogicService.getUserDatabyUser(userEdit)
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
        def data = userLogicService.getUserDatabyUser(user)
        bindData(data.userPaymentInfo, params)
        bindData(data.userShippingInfo, params)
        try {
            userService.save(user)
            shippingInfoService.save(data.userShippingInfo)
            paymentInfoService.save(data.userPaymentInfo)   
        } catch (ValidationException e) {
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
        def data = userLogicService.getUserDatabyId(id)
        data.userPaymentInfo.delete()
        data.userShippingInfo.delete()
        userLogicService.deleteUserRolesById(id)
        userLogicService.deleteUserOrdersById(id)
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
