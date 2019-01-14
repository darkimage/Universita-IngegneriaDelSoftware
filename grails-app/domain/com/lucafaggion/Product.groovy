package com.lucafaggion

class Product {
    String identifier
    String name
    String description
    Date creation_date
    int quantity
    int price
    String toString(){
        "${name}"
    } 
    static belongsTo = [category: ProductCategory]
    static constraints = {
        name()
        description()
        price()
        quantity()
        identifier display:false
        creation_date display:false  
    }

}
