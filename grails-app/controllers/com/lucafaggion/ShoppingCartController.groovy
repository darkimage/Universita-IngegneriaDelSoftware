package com.lucafaggion
import grails.validation.ValidationException
import com.bertramlabs.plugins.selfie.AttachmentValueConverter
import grails.plugin.springsecurity.annotation.Secured

@Secured('isFullyAuthenticated()')
class ShoppingCartController {
    OrderslogicService orderslogicService
    LineitemService lineitemService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def cartItems = orderslogicService.getUserShoppingCart(params)
        def orderprice = orderslogicService.calculateTotalPrice(cartItems)
        render(view:'index',model:[cartItems:cartItems,itemsCount:cartItems.size(),totalprice:orderprice, params:params])
    }

    def add(Integer id,Integer quantity){
        def shoppingcart
        try{
            orderslogicService.addToUserCart(id,quantity)
        }catch(Exception e){
            notFound()
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'com.lucafaggion.ShoppingCart.added')
                redirect(controller:params.lastController,action:params.lastAction,params:params)
            }
            '*' { respond shoppingcart, [status: OK] }
        }

    }

    def update(Integer id,Integer value){
        def lineItem = lineitemService.get(id)
        if (lineItem == null) {
            notFound()
            return
        }
        try{
            if(params.delete){
                orderslogicService.deleteLineItem(id)
            }else{
                orderslogicService.updateCartProduct(id,value)
            }
        }catch (Exception e) {
            return response.sendError(500)
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'com.lucafaggion.ShoppingCart.updated')
                redirect(action:'index')
            }
            '*' { respond null, [status: OK] }
        }
    }

    def updateCart = {
        if(params.placeorder != null){
            def userorder
            try{
                userorder = orderslogicService.placeUserOrder()
            }catch(ControllerException ce){
                def error = ce.getExceptionMessage()
                flash.error = message(code:error.code,args:error.args)
                redirect(action:'index')
                return
            }
            request.withFormat { 
                form multipartForm {
                    flash.message = message(code: 'com.lucafaggion.ShoppingCart.ordersucces',args:[userorder.id])
                    redirect(controller:'orders',action:'show',id:userorder.id)
                }
                '*' { respond userorder, [status: CREATED] }
            }
        }else{
            try{
                orderslogicService.deleteShoppingCart()
            }catch (Exception e){
                flash.error = message(code:e.getMessage())
                redirect(action:'index')
                return
            }
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'com.lucafaggion.ShoppingCart.orderclear')
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: OK }
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            '*'{ render status: 404 }
        }
    }
}
