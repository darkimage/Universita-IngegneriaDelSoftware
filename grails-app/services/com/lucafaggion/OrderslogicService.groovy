package com.lucafaggion
import grails.gorm.transactions.Transactional
import com.lucafaggion.auth.*

@Transactional
class OrderslogicService {
    def springSecurityService
    LineitemService lineitemService
    OrdersService ordersService

    private def getUserShoppingCartOrder(){
        def user = springSecurityService.getCurrentUser()
        def query = "FROM Orders o WHERE o.state='cart'"
        def cart = Orders.findAll(query,[max:1])[0]
        return cart
    }

    def getUserShoppingCart(params) {
        def user = springSecurityService.getCurrentUser()
        def query = "FROM LineItem l inner join fetch l.orderid as o WHERE o.user = " + user.id + " AND o.state='cart'"
        def cartItems = LineItem.findAll(query)
        //Update items
        for (item in cartItems) {
            updateCartProduct(item.id.toInteger(),item.quantity)
        }
        return LineItem.findAll(query)
        //Product.findAll(query,[max: params.max.toInteger(), offset: params.offset]))
    }

    def calculateLineItemPrice(Product product,Integer quantity){
        return product.price * quantity
    }

    def deleteLineItem(Integer id){
        lineitemService.delete(id);
    }

    def calculateTotalPrice(items){
        def price = 0
        for (item in items) {
            price+= item.price
        }
        return price
    }

    def updateCartProduct(Integer id,Integer value){
        def query = "FROM LineItem l WHERE l.id =" + id
        def lineitem = LineItem.findAll(query,[max:1])[0]
        lineitem.quantity = value
        lineitem.price = calculateLineItemPrice(lineitem.subProduct,value)
        lineitemService.save(lineitem)
    }

    def placeUserOrder(){
        def cart = getUserShoppingCartOrder()
        cart.state = "placed"
        ordersService.save(cart)
        //TODO
        //rimuovere la quantita' di ogni lineitem da ogni prodotto
        return cart
    }

    def deleteShoppingCart(){
        def cart = getUserShoppingCartOrder()
        def query = "FROM LineItem l WHERE l.orderid =" + cart.id
        def lineitems = LineItem.findAll(query)
        for (lineitem in lineitems) {
            lineitemService.delete(lineitem.id)
        }
        ordersService.delete(cart.id)
    }
}
