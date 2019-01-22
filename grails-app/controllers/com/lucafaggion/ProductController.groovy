package com.lucafaggion
import grails.validation.ValidationException
import com.bertramlabs.plugins.selfie.AttachmentValueConverter
import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class ProductController {
    static scaffold = Product
    UtilityService utilityService
    ProductService productService
    ProductlogicService productlogicService

    def index(Integer max) {
        redirect(action:'listProductCompact')
    }

    @Secured('permitAll') // change to @Secured('ROLE_DIPENDENTE')
    def listProductCompact(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        //respond productService.list(params), model:[productCount: productService.count()]
        render(view:"index",model:[productList:productService.list(params),productCount: productService.count()])
    }

    def listProducts = {
        def category = ProductCategory.list()
        def currentCat = (params.cat != null) ? params.cat : category[0].id
        def productsSize = productlogicService.getCategoryProductCount(currentCat)
        def products = productlogicService.getProductsOfCategory(currentCat,params)
        render(view:"listProductCat",model:  
        [categories: category,productList:products,productCount: productsSize,params:params])
    }

    def show(Long id) {
        //utilityService.printClass(productService.get(id).photo.inputStream)
        println productService.get(id).photo.cloudFile
        respond productService.get(id)
    }

    def save(Product product) {
        if (product == null) {
            notFound()
            return
        }
        product = productlogicService.setUpProduct(product,request,params)

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
