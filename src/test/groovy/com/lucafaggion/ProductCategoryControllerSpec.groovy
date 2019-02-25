package com.lucafaggion

import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import grails.buildtestdata.BuildDataTest
import spock.lang.Specification

class ProductCategoryControllerSpec extends Specification implements ControllerUnitTest<ProductCategoryController>, BuildDataTest  {

    void setupSpec() {
        mockDomain ProductCategory
        mockDomain Product
    }

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'TestCategory'
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.productCategoryLogicService = Mock(ProductCategoryLogicService) {
            1 * getCategories(_) >> [cat:[],count:[]]
        }
        controller.productCategoryService = Mock(ProductCategoryService){
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.categories.cat
        !model.categories.count
        model.productCategoryCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.productCategory!= null
    }

    void "Test the save action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/productCategory/index'
        flash.message != null
    }

    void "Test the save action correctly persists"() {
        given:
        controller.productCategoryService = Mock(ProductCategoryService) {
            1 * save(_ as ProductCategory)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def productCategory = new ProductCategory(params)
        productCategory.id = 1

        controller.save(productCategory)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/productCategory/show/1'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.productCategoryService = Mock(ProductCategoryService) {
            1 * save(_ as ProductCategory) >> { ProductCategory productCategory ->
                throw new ValidationException("Invalid instance", productCategory.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def productCategory = new ProductCategory()
        controller.save(productCategory)

        then:"The create view is rendered again with the correct model"
        model.productCategory != null
        view == 'create'
    }

    void "Test the show action with a null id"() {
        given:
        controller.productCategoryService = Mock(ProductCategoryService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.productCategoryService = Mock(ProductCategoryService) {
            1 * get(2) >> new ProductCategory()
        }
        controller.productlogicService = Mock(ProductlogicService){
            1 * getProductsOfCategory(2,_) >> [Product.build(),Product.build(),Product.build()]
            1 * getCategoryProductCountById(2) >> 3
        }


        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.productCategory instanceof ProductCategory
        model.productCount == 3
        model.productsList.size() == 3
    }

    void "Test the edit action with a null id"() {
        given:
        controller.productCategoryService = Mock(ProductCategoryService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.productCategoryService = Mock(ProductCategoryService) {
            1 * get(2) >> new ProductCategory()
        }

        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.productCategory instanceof ProductCategory
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/productCategory/index'
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.productCategoryService = Mock(ProductCategoryService) {
            1 * save(_ as ProductCategory)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def productCategory = new ProductCategory(params)
        productCategory.id = 1

        controller.update(productCategory)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/productCategory/show/1'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.productCategoryService = Mock(ProductCategoryService) {
            1 * save(_ as ProductCategory) >> { ProductCategory productCategory ->
                throw new ValidationException("Invalid instance", productCategory.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(new ProductCategory())

        then:"The edit view is rendered again with the correct model"
        model.productCategory != null
        view == 'edit'
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/productCategory/index'
        flash.message != null
    }

    void "Test the delete action with an instance"() {
        given:
        controller.productCategoryLogicService = Mock(ProductCategoryLogicService) {
            1 * deleteCategory(2)
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/productCategory/index'
        flash.message != null
    }
}