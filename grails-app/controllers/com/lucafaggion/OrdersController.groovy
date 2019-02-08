package com.lucafaggion
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.SpringSecurityUtils

@Secured('isFullyAuthenticated()') 
class OrdersController {

    OrdersService ordersService
    OrderslogicService orderslogicService
    LineItemLogicService lineItemLogicService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond orderslogicService.getOrders(params), model:[ordersCount: ordersService.count()]
    }

    def show(Long id) {
        def order = ordersService.get(id)
        def items = lineItemLogicService.getAllLineItemsOfOrder(order,params)
        def hasPermission = false

        if(SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN,ROLE_DIPENDENTE')){
            hasPermission = true
        }

        if(order.user == springSecurityService.getCurrentUser()){
            hasPermission = true
        }

        if(hasPermission){
            respond([orders:order,lineItems:items])
        }else{
           render(view: '/login/denied')
        }

    }

    def save(Orders orders) {
        if (orders == null) {
            notFound()
            return
        }

        try {
            ordersService.save(orders)
        } catch (ValidationException e) {
            respond orders.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'orders.label', default: 'Orders'), orders.id])
                redirect orders
            }
            '*' { respond orders, [status: CREATED] }
        }
    }

    def update(Orders orders) {
        if (orders == null) {
            notFound()
            return
        }

        try {
            ordersService.save(orders)
        } catch (ValidationException e) {
            respond orders.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'orders.label', default: 'Orders'), orders.id])
                redirect orders
            }
            '*'{ respond orders, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        
        orderslogicService.deleteOrder(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'orders.label', default: 'Orders'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'orders.label', default: 'Orders'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
