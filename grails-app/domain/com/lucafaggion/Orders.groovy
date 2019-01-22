package com.lucafaggion
import com.lucafaggion.auth.*

class Orders {
    String state
    int price
    static hasMany = [lineItem: LineItem]
    static belongsTo = [user: User]
    static constraints = {
        state()
        price()
        lineItem()
        user() 
        lineItem widget: 'select'
    }
}
