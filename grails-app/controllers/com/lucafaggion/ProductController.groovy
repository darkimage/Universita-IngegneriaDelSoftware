package com.lucafaggion
import grails.validation.ValidationException
import com.bertramlabs.plugins.selfie.AttachmentValueConverter

class ProductController {
    static scaffold = Product
    ProductService productService
    ProductlogicService productlogicService
    def global = new Globals()

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond productService.list(params), model:[productCount: productService.count()]
    }

    def test = {
        session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] = new Locale("en","US")
        def category = ProductCategory.list()
        def currentCat = (params.cat != null) ? params.cat : category[0].id
        def productsSize = productlogicService.getCategoryProductCount(currentCat)
        def products = productlogicService.getProductsOfCategory(currentCat,params)
        render(view:"listProductCat",model:  
        [categories: category,productList:products,productCount: productsSize])
    }

    def show(Long id) {
        //global.printClass(productService.get(id).photo.inputStream)
        println productService.get(id).photo.cloudFile
        respond productService.get(id)
    }

    def save(Product product) {
        if (product == null) {
            notFound()
            return
        }
        product.creation_date = new Date()
        if(!params.hasIdentifier){
            product.identifier = "PLACEHOLDER"
        }

        try {
            productService.save(product)
            if(!params.hasIdentifier){
                productlogicService.generateIdentifier(product)
            }
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
