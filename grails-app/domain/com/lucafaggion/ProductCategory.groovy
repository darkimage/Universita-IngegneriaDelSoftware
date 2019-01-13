package com.lucafaggion

class ProductCategory {
    String name
    static hasMany = [subProduct : Product]
    static constraints = {
    }
}
