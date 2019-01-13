package com.lucafaggion

class Orders {
    String state
    int price
    static hasMany = [lineItem: LineItem]
    static belongsTo = [shippingInfo: ShippingInfo]
    static constraints = {
        state()
        price()
        lineItem()
        shippingInfo()
        lineItem widget: 'select'
    }
}
