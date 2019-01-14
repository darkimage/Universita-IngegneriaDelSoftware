package com.lucafaggion

import grails.gorm.transactions.Transactional

@Transactional
class ProductLogicService {

    @Transactional
    def getProductsOfCategory(value,params){
        params.max = (params.max != null) ? params.max : 5;
        def sort = (params.sort ? "ORDER BY " + params.sort : "") 
        def order = (params.order ? params.order : "")
        def query = "FROM Product as P WHERE P.category = '" + value + "' " + sort + " " + order
        return (Product.findAll(query,[max: params.max.toInteger(), offset: params.offset]))
    }

    @Transactional
    def getCategoryProductCount(value){
        def query = "FROM Product as P WHERE P.category = '" + value + "'"
        return (Product.findAll(query).size())
    }
    
    @Transactional
    void generateIdentifier(Product product){
        def randChars = ""
        for(Integer i=0 ; i<3 ; i++) {
            randChars+= product.name.trim()[global.getRandomNumber(product.name.size())]
        }
        product.identifier = ProductCategory.findAll("SELECT name FROM ProductCategory Where id="+product.category.id)[0].substring(0,3).toUpperCase()+product.id+randChars.toUpperCase()
    }
}
