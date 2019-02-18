package com.lucafaggion
import grails.gorm.transactions.Transactional
import com.lucafaggion.auth.*

@Transactional
class OrderslogicService {
    def springSecurityService
    LineitemService lineitemService
    OrdersService ordersService
    ProductService productService
    LineItemLogicService lineItemLogicService

    private def getUserShoppingCartOrder(){
        def user = springSecurityService.getCurrentUser()
        if(user != null) {
            def query = "FROM Orders o WHERE o.state='cart' AND o.user=:user"
            def cart = Orders.find("FROM Orders o WHERE o.state='cart' AND o.user=?", [user])
            if (cart == null) {
                cart = new Orders(user: user, price: 0, state: 'cart', submittedDate: new Date());
                ordersService.save(cart)
            }
            return cart
        }else{
            return null
        }
    }

    def getUserShoppingCart(params=[:]) {
        def cartorder = getUserShoppingCartOrder()
        def query = "FROM LineItem l WHERE l.orderid.id=:order"
        def cartItems = lineItemLogicService.getAllLineItemsOfOrder(cartorder,params)
        for (item in cartItems) { 
            updateCartProduct(item.id.toInteger(),item.quantity)
        }
        return cartItems
    }

    def addToUserCart(Long id,Integer quantity) {
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

    def checkIteminOrder(Orders order,Long id){
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
    
    def calculateTotalPrice(items){
        def price = 0
        for (item in items) {
            price+= item.price
        }
        return price
    }

    def updateCartProduct(Long id,Integer value){
        def lineitem = lineitemService.get(id)
        lineitem.quantity = value
        lineitem.price = calculateLineItemPrice(lineitem.subProduct,value)
        lineitemService.save(lineitem)
    }

    def placeUserOrder(){
        def cart = getUserShoppingCartOrder()
        cart.state = "placed"
        def items = getUserShoppingCart()
        for (lineitem in items) {
            int quantity = lineitem.quantity
            Product product = lineitem.subProduct
            if(product.quantity < quantity){ 
                throw new ControllerException(code:'com.lucafaggion.ShoppingCart.productunavaible',args:[product.name])
            }else{
                product.quantity -=  quantity
                productService.save(product)
            }
        }
        cart.submittedDate = new Date()
        cart.price = calculateTotalPrice(items)
        ordersService.save(cart)
        return cart
    }

    def deleteShoppingCart(){
        def cart = getUserShoppingCartOrder()
        def lineitems = lineItemLogicService.getAllLineItemsOfOrderById(cart.id)
        for (lineitem in lineitems) {
            lineitemService.delete(lineitem.id)
        }
        ordersService.delete(cart.id)
    }

    def deleteOrder(id){
        def items = lineItemLogicService.getAllLineItemsOfOrderById(id)
        for (item in items) {
            item.delete()
        }
        ordersService.delete(id)
    }

    def getOrders(params=[:]) {
        if(params.sort == 'lineItem'){
            def orders = []
            def query = Orders.findAll("SELECT o, COUNT(*) as countid FROM Orders as o LEFT OUTER JOIN LineItem as l ON o.id=l.orderid.id GROUP BY o.id ORDER BY countid " + params.order,[max:params.max,offset:params.offset])
            for (order in query) {
                orders.add(order[0])
            }
            return orders
        }else{
            return ordersService.list(params)
        }
    }
}
 