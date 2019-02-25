package com.lucafaggion
import grails.validation.ValidationException
import com.bertramlabs.plugins.selfie.AttachmentValueConverter
import grails.plugin.springsecurity.annotation.Secured
import com.bertramlabs.plugins.selfie.*
import org.springframework.web.multipart.*
import org.springframework.web.multipart.commons.*
import org.apache.commons.io.FileUtils
import org.apache.commons.fileupload.disk.DiskFileItem
import org.springframework.context.MessageSource
import org.springframework.web.servlet.support.RequestContextUtils

@Secured('permitAll')
class ProductController {
    MessageSource messageSource

    UtilityService utilityService
    ProductService productService
    ProductlogicService productlogicService
    ProductCategoryLogicService productCategoryLogicService

    def index(Integer max) {
        params.max = Math.min(max  ?: 10, 100)
        params.offset = (params.offset) ? params.offset : 0
        def featured = productlogicService.getfeaturedProducts()
        def productCategories = productCategoryLogicService.getCategories()
        def newestProducts = []
        for (category in productCategories.cat) {
            newestProducts.add(productlogicService.getProductsOfCategory(category.id,[max:3,order:'desc',sort:'creation_date']))
        }
        render(view:'main',model:[featuredList:featured,categories:productCategories,newestProducts:newestProducts])
    }
    
    @Secured(['ROLE_DIPENDENTE','ROLE_ADMIN'])
    def manage(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def data = productlogicService.getProductData(params)
        render(view:"index",model:[categories:data.cat,productList:data.list,productCount: data.count,flash:[message:params.flash]])
    }

    def list(Integer max) {
        params.max = Math.min(max  ?: 10, 100)
        def data = productlogicService.getProductData(params)
        render(view:"list",model:[categories:data.cat,productList:data.list,productCount: data.count,params:params])
    }

    def search(Integer max){
        params.value = (params.value) ? params.value  : messageSource.getMessage('com.lucafaggion.Product.searchnovalue', null, "No Search", RequestContextUtils.getLocale(request))
        params.max = Math.min(max ?: 10, 100)
        def data = productlogicService.getProductsFromSearch(params)
        render(view:"search",model:[productList:data.list,productCount: data.count,params:params])
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

        productlogicService.deleteProduct(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'product.label', default: 'Product'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: 200 }
        }
    }

    @Secured(['ROLE_DIPENDENTE','ROLE_ADMIN'])
    def save(Product product) {
        if (product == null) {
            notFound()
            return
        }

        product.featured = (product.featured) ? product.featured : false
        if(params.update){
            update(product)
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
        if (product == null) {
            notFound()
            return
        }
        product.featured = (product.featured) ? product.featured : false

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
 