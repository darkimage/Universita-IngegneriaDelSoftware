package com.lucafaggion
import grails.validation.ValidationException
import com.bertramlabs.plugins.selfie.AttachmentValueConverter
import grails.plugin.springsecurity.annotation.Secured

@Secured('isFullyAuthenticated()')
class ShoppingCartController {
    OrderslogicService orderslogicService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def cartItems = orderslogicService.getUserShoppingCart(params)
        def orderprice = orderslogicService.calculateTotalPrice(cartItems)
        render(view:'index',model:[cartItems:cartItems,itemsCount:cartItems.size(),totalprice:orderprice, params:params])
    }

    def add(Integer id,Integer quantity){
        //render "productid=" + id + "|quantity="+ quantity + "|params="+params
        def shoppingcart
        try{
            orderslogicService.addToUserCart(id,quantity)
        }catch(Exception e){
            return response.sendError(500)
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
            }catch (Exception e) {
                return response.sendError(500)
            }
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'com.lucafaggion.ShoppingCart.ordersucces',args:[userorder.id])
                    redirect(controller:'orders',action:'showUserOrders')
                }
                '*' { respond userorder, [status: CREATED] }
            }
        }else{
            try{
                orderslogicService.deleteShoppingCart()
            }catch (Exception e){
                return response.sendError(500)
            }
            request.withFormat {
                form multipartForm {
                    flash.message = message(code: 'com.lucafaggion.ShoppingCart.orderclear')
                    redirect action:"index", method:"GET"
                }
                '*'{ render status: NO_CONTENT }
            }
        }
    }
}
