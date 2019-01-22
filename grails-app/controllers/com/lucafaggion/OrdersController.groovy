package com.lucafaggion
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured('isFullyAuthenticated()') 
class OrdersController {

    OrdersService ordersService
    OrderslogicService orderslogicService 

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ordersService.list(params), model:[ordersCount: ordersService.count()]
    }

    def show(Long id) {
        respond ordersService.get(id)
    }

    def create() {
        respond new Orders(params)
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

    def edit(Long id) {
        respond ordersService.get(id)
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

        ordersService.delete(id)

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
