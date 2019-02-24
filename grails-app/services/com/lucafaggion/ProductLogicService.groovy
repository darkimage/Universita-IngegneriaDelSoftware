package com.lucafaggion

import grails.gorm.transactions.Transactional


@Transactional
class ProductlogicService {
    UtilityService utilityService
    ProductCategoryService productCategoryService
    PriceConverterService priceConverterService

    def getfeaturedProducts(){
        return Product.findAll("FROM Product as p WHERE p.featured = TRUE")
    }

    def getProductData(params){
        def category = productCategoryService.list()
        def currentCat = (params.cat != null) ? params.cat : ((category.size() > 0) ? category[0].id : 0)
        def productsSize = getCategoryProductCount(currentCat) 
        def products = getProductsOfCategory(currentCat,params)
        return [cat:category,list:products,count:productsSize]
    }

    def getProductsFromSearch(params){
        if(params.value != null){
            def countquery = "SELECT COUNT(*) "
            def query = "FROM Product as p WHERE "
            def map = ['identifier','name','description','creation_date','quantity','price','category.name']
            for (Integer i = 0; i < 7; i++) {
                query += "p." + map[i] + " LIKE " + ":value"
                if(i != 6){
                    query += " OR "
                }
            }
            def products = Product.findAll(query,[value:"%"+params.value+"%"],[max:params.max,offset:params.offset])
            Long productsSize = Product.executeQuery(countquery+query,[value:"%"+params.value+"%"])[0]
            return [list:products,count:productsSize]
        }else{
            return getProductData(params)
        }
    }

    def getProductsOfCategory(value,params){
        params.max = (params.max != null) ? params.max : 5;
        def sort = (params.sort ? "ORDER BY " + params.sort : "") 
        def order = (params.order ? params.order : "")
        def query = "FROM Product as P WHERE P.category = '" + value + "' " + sort + " " + order
        return (Product.findAll(query,[max: params.max.toInteger(), offset: params.offset]))
    }

    def getCategoryProductCount(value){
        def query = "FROM Product as P WHERE P.category = '" + value + "'"
        return (Product.findAll(query).size())
    }

    def getCategoryProductCountById(Long id){
        def query = "FROM Product as P WHERE P.category.id = '" + id + "'"
        return (Product.findAll(query).size())
    }
    
    void generateIdentifier(Product product){
        def randChars = ""
        for(Integer i=0 ; i<3 ; i++) {
            randChars+= product.name.replaceAll("\\s","")[utilityService.getRandomNumber(product.name.replaceAll("\\s","").size())]
        }
        product.identifier = ProductCategory.findAll("SELECT name FROM ProductCategory Where id="+product.category.id)[0].substring(0,3).toUpperCase()+product.id+randChars.toUpperCase()
    }

    Product setUpProduct(Product product,request,params){
        product.creation_date = new Date()
        if(!params.hasIdentifier){
            product.identifier = "PLACEHOLDER"
        }
        product.price = priceConverterService.convertPriceToStore(params.price,request)
        return product
    }
}
