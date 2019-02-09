package com.lucafaggion
import grails.validation.ValidationException
import com.bertramlabs.plugins.selfie.AttachmentValueConverter
import grails.plugin.springsecurity.annotation.Secured
import com.bertramlabs.plugins.selfie.*
import org.springframework.web.multipart.*
import org.springframework.web.multipart.commons.*
import org.apache.commons.io.FileUtils
import org.apache.commons.fileupload.disk.DiskFileItem

@Secured('permitAll')
class ProductController {
    //static scaffold = Product
    UtilityService utilityService
    ProductService productService
    ProductlogicService productlogicService

    def index(Integer max) {
        def featured = productlogicService.getfeaturedProducts()
        render(view:'main',model:[featuredList:featured])
    }
    
    @Secured(['ROLE_DIPENDENTE','ROLE_ADMIN']) // change to @Secured('ROLE_DIPENDENTE')
    def manage(Integer max) {
        def data = productlogicService.getProductData(params)
        render(view:"index",model:[categories:data.cat,productList:data.list,productCount: data.count,flash:[message:params.flash]])
    }

    def list = {
        def data = productlogicService.getProductData(params)
        render(view:"listProductCat",model:  
        [categories:data.cat,productList:data.list,productCount: data.count,params:params])
    }

    def show(Long id) {
        respond productService.get(id)
    }

    @Secured(value=["hasAnyRole('ROLE_DIPENDENTE','ROLE_ADMIN')"])
    def create(){
        respond new Product(params)
    }

    @Secured(value=["hasAnyRole('ROLE_DIPENDENTE','ROLE_ADMIN')"]) 
    def edit(Long id) {
        respond productService.get(id)
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
        product.featured = (product.featured) ? product.featured : false
        if(params.update){
            update(product)
            return
        }
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

    @Secured(['ROLE_DIPENDENTE','ROLE_ADMIN'])
    def update(Product product) {
        product.featured = (product.featured) ? product.featured : false
        if (product == null) {
            notFound()
            return
        }

        try {
            productService.save(product)
        } catch (ValidationException e) {
            respond product.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'product.label', default: 'Product'), product.id])
                redirect product
            }
            '*'{ respond product, [status: OK] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'com.lucafaggion.Product.DomainName', default: 'Product'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: 404 }
        }
    }
}
 