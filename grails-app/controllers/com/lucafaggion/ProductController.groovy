package com.lucafaggion

class ProductController {
    static scaffold = Product
    //def index() {
        //redirect(action: "show")
    //}

    def test = {
        params.max = 2
        params.controllerName = ["Product"]
        def category = ProductCategory.list()
        def currentCat = (params.cat != null) ? params.cat : category[0].id
        def productsSize = getCategoryProductCount(currentCat)
        def products = getProductsOfCategory(currentCat)
        //def products = Product.findAll([max: params.max, order: "desc", offset: params.offset])
        render(view:"listProductCat",model:  
        [categories: category,productList:products,productCount: productsSize])
    }

    def current = { 
        def allProducts = Product.list();
        [tt:allProducts,args:params]
    }

    def getProductsOfCategory(value){
        def query = "FROM Product as P WHERE P.category = '" + value + "' " + "ORDER BY " + params.sort + " " + params.order
        return (Product.findAll(query,[max: params.max, offset: params.offset]))
    }

    def getCategoryProductCount(value){
        def query = "FROM Product as P WHERE P.category = '" + value + "'"
        return (Product.findAll(query).size())
    }
 
 
    //def save = {
    //    def test = [params]
    //    redirect(action:"current",params:[author: "Stephen King"])
    //}

    def receiveJson = {
        def json = request.JSON;
        println json
        render json
    }
}
