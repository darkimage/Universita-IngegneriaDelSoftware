package com.lucafaggion

class ProductController {
    static scaffold = Product
    //def index() {
        //redirect(action: "show")
    //}

    def test = {
        render(view:"current",layout:"layout/main")
    }

    def current = {
        def allProducts = Product.list();
        [tt:allProducts,args:params]
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
