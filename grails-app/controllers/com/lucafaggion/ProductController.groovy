package com.lucafaggion
import grails.validation.ValidationException
import com.bertramlabs.plugins.selfie.AttachmentValueConverter
import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class ProductController {
    //static scaffold = Product
    UtilityService utilityService
    ProductService productService
    ProductlogicService productlogicService

    def index(Integer max) {
        redirect(action:'listProductCompact',params:[flash:flash.message])
    }

    @Secured(['ROLE_DIPENDENTE','ROLE_ADMIN']) // change to @Secured('ROLE_DIPENDENTE')
    def listProductCompact(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        render(view:"index",model:[productList:productService.list(params),productCount: productService.count(),flash:[message:params.flash]])
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
        respond productService.get(id)
    }

    @Secured(['ROLE_DIPENDENTE','ROLE_ADMIN'])
    def create(){
        respond new Product(params)
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        productService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'product.label', default: 'Product'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    @Secured(['ROLE_DIPENDENTE','ROLE_ADMIN'])
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
