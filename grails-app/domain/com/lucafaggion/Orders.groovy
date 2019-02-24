package com.lucafaggion
import com.lucafaggion.auth.*

class Orders {
    String state
    int price
    Date submittedDate
    static hasMany = [lineItem: LineItem]
    static belongsTo = [user: User, paymentDetails: PaymentInfo, shippingDetails: ShippingInfo]
    static constraints = {
        state()
        price()
        lineItem()
        user() 
        lineItem widget: 'select'
    }
}
