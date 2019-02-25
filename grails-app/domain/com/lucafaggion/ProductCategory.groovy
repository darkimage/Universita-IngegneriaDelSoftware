package com.lucafaggion

class ProductCategory {
    String name
    //static hasMany = [subProduct : Product]

    String toString(){
        "${name}"
    }
    static constraints = {

    }
//    static mapping = {
//        cascade: 'save-update'
//    }
}
