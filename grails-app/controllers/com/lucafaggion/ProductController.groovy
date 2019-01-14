package com.lucafaggion
import grails.validation.ValidationException

class ProductController {
    static scaffold = Product
    ProductService productService
    ProductLogicService productlogicService = new ProductLogicService()
    def global = new Globals()

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond productService.list(params), model:[productCount: productService.count()]
    }

    def test = {
        def category = ProductCategory.list()
        def currentCat = (params.cat != null) ? params.cat : category[0].id
        def productsSize = productlogicService.getCategoryProductCount(currentCat)
        def products = productlogicService.getProductsOfCategory(currentCat,params)
        render(view:"listProductCat",model:  
        [categories: category,productList:products,productCount: productsSize])
    }

    def show(Long id) {
        respond productService.get(id)
    }

    def save(Product product) {
        if (product == null) {
            notFound()
            return
        }
        product.creation_date = new Date()
        product.identifier = "PLACEHOLDER"

        try {
            productService.save(product)
            generateIdentifier(product)
            productService.save(product)
        } catch (ValidationException e) {
            respond product.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'product.label', default: 'Product'), product.id])
                redirect product
            }
            '*' { respond product, [status: CREATED] }
        }
    }
}
