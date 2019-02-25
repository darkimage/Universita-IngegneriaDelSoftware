package com.lucafaggion

import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import grails.buildtestdata.BuildDataTest
import spock.lang.Specification
import org.springframework.mock.web.MockMultipartFile

class ProductControllerSpec extends Specification implements ControllerUnitTest<ProductController>, BuildDataTest {

    void setupSpec() {
        mockDomain Product
    }

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'Product1'
        params["identifier"] = 'CATPDD1'
        params["description"] = 'This is a Test Description'
        params["creation_date"] = new Date()
        params["quantity"] = 100
        params["price"] = 2099
        params["photo"] = new MockMultipartFile("file.jpeg", "file.jpeg", "image/jpeg", (InputStream) this.class.classLoader.getResourceAsStream("image.jpg"))
        params["featured"] = true
        params["hasIdentifier"] = true
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.productCategoryLogicService = Mock(ProductCategoryLogicService) {
            1 * getCategories() >> [cat:[],count:[]]
        }
        controller.productlogicService = Mock(ProductlogicService) {
            1 * getfeaturedProducts() >> []

        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.categories.cat
        !model.categories.count
        !model.featuredList
        !model.newestProducts
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.product!= null
    }

    void "Test the save action with a null instance"() {

        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        flash.message != null
    }

    void "Test the save action correctly persists"() {
        given:
        controller.productlogicService = Mock(ProductlogicService){
            1* setUpProduct(_,_,_) >> { args -> return args.get(0)}
        }
        controller.productService = Mock(ProductService){
            2 * save(_ as Product)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def product = new Product(params)
        product.id = 1

        controller.save(product)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/product/show/1'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.productlogicService = Mock(ProductlogicService){
            1* setUpProduct(_,_,_) >> { args -> return args.get(0)}
        }
        controller.productService = Mock(ProductService) {
            1 * save(_ as Product) >> { Product product ->
                throw new ValidationException("Invalid instance", product.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def product = new Product()
        controller.save(product)

        then:"The create view is rendered again with the correct model"
        model.product != null
        view == 'create'
    }

    void "Test the show action with a null id"() {
        given:
        controller.productService = Mock(ProductService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.productService = Mock(ProductService) {
            1 * get(2) >> new Product()
        }


        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.product instanceof Product
    }

    void "Test the edit action with a null id"() {
        given:
        controller.productService = Mock(ProductService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.productService = Mock(ProductService) {
            1 * get(2) >> new Product()
        }

        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.product instanceof Product
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.productService = Mock(ProductService) {
            1 * save(_ as Product)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def product = new Product(params)
        product.id = 1

        controller.update(product)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/product/show/1'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.productService = Mock(ProductService) {
            1 * save(_ as Product) >> { Product product ->
                throw new ValidationException("Invalid instance", product.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(new Product())

        then:"The edit view is rendered again with the correct model"
        model.product != null
        view == 'edit'
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        flash.message != null
    }

    void "Test the delete action with an instance"() {
        given:
        controller.productlogicService = Mock(ProductlogicService) {
            1 * deleteProduct(2)
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        flash.message != null
    }
}