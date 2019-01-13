package com.lucafaggion

class LineItem {
    int quantity
    int price
    static belongsTo = [subProduct: Product, orderid:Orders]
    static constraints = {

    }
}
