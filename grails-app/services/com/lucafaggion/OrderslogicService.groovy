package com.lucafaggion
import grails.gorm.transactions.Transactional
import com.lucafaggion.auth.*

@Transactional
class OrderslogicService {
    def springSecurityService
    LineitemService lineitemService
    OrdersService ordersService
    ProductService productService

    private def getUserShoppingCartOrder(){
        def user = springSecurityService.getCurrentUser()
        def query = "FROM Orders o WHERE o.state='cart' AND o.user=:user"
        def cart = Orders.findAll(query,[user:user],[max:1])[0]
        if(cart == null){
           cart = new Orders(user:user,price:0,state:'cart');
           ordersService.save(cart)
        }
        return cart
    }

    def getUserShoppingCart(params=[:]) {
        def cartorder = getUserShoppingCartOrder()
        def query = "FROM LineItem l inner join fetch l.orderid as o WHERE o.id=:orderID"
        def cartItems
        if(params.sort != null && params.order != null){
                query += " ORDER BY l." + params.sort + " " + params.order
                cartItems = LineItem.findAll(query,[orderID:cartorder.id])
        }else{
            cartItems = LineItem.findAll(query,[orderID:cartorder.id])
        }
        //Update items
        for (item in cartItems) { 
            updateCartProduct(item.id.toInteger(),item.quantity)
        }
        return cartItems
    }

    def addToUserCart(Integer id,Integer quantity) {
        def user = springSecurityService.getCurrentUser()
        def cart = getUserShoppingCartOrder()
        def item = checkIteminOrder(cart,id)
        if(item){
            item.quantity += quantity;
        }else{
            item = new LineItem(subProduct:Product.get(id),quantity:quantity,orderid:cart)
        }
        lineitemService.save(item)
        return item
    }

    def checkIteminOrder(Orders order,Integer id){
        for(item in order.lineItem){
            if(item.subProduct.id == id){
                return item
            }
        }
        return null
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
        println "PLACED"
        def cart = getUserShoppingCartOrder()
        cart.state = "placed"
        def test = getUserShoppingCart()
        for (lineitem in getUserShoppingCart()) {
            int quantity = lineitem.quantity
            Product product = lineitem.subProduct
            println "(product.quantity < quantity)"
            if(product.quantity < quantity){ 
                println "ERROR"
                throw new ControllerException(code:'com.lucafaggion.ShoppingCart.productunavaible',args:[product.name])
            }else{
                product.quantity -=  quantity
                productService.save(product)
            }
        }
        ordersService.save(cart)
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
 