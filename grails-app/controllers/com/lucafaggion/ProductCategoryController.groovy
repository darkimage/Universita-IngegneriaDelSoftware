package com.lucafaggion

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(value=["hasAnyRole('ROLE_DIPENDENTE','ROLE_ADMIN')"])
class ProductCategoryController {
    ProductlogicService productlogicService
    ProductCategoryLogicService productCategoryLogicService
    ProductCategoryService productCategoryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def categories = productCategoryLogicService.getCategories(params)
        def count = productCategoryService.count()
        render(view:'index',model:[categories:categories,productCategoryCount:count])
    }

    def show(Long id) {
        def categoryData = productCategoryService.get(id)
        if(categoryData != null) {
            def products = productlogicService.getProductsOfCategory(id, params)
            def count = productlogicService.getCategoryProductCountById(id)
            respond([productCategory: categoryData, productCount: count, productsList: products])
        }else{
            notFound()
        }
    } 

    def create() {
        respond new ProductCategory(params)
    }

    def save(ProductCategory productCategory) {
        if (productCategory == null) {
            notFound()
            return
        }

        try {
            productCategoryService.save(productCategory)
        } catch (ValidationException e) {
            respond productCategory.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'productCategory.label', default: 'ProductCategory'), productCategory.id])
                redirect productCategory
            }
            '*' { respond productCategory, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond productCategoryService.get(id)
    }

    def update(ProductCategory productCategory) {
        if (productCategory == null) {
            notFound()
            return
        }

        try {
            productCategoryService.save(productCategory)
        } catch (ValidationException e) {
            respond productCategory.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'productCategory.label', default: 'ProductCategory'), productCategory.id])
                redirect productCategory
            }
            '*'{ respond productCategory, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        productCategoryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'productCategory.label', default: 'ProductCategory'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: 200 }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'productCategory.label', default: 'ProductCategory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: 404 }
        }
    }
}
