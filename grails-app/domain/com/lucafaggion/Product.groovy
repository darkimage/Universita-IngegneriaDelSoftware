package com.lucafaggion

class Product {
    String name
    String description
    Date creation_date
    int quantity
    String price
    static belongsTo = [category: ProductCategory]
    static constraints = {
    }
}
