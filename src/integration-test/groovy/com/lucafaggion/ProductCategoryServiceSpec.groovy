package com.lucafaggion

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProductCategoryServiceSpec extends Specification {

    ProductCategoryService productCategoryService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ProductCategory(...).save(flush: true, failOnError: true)
        //new ProductCategory(...).save(flush: true, failOnError: true)
        //ProductCategory productCategory = new ProductCategory(...).save(flush: true, failOnError: true)
        //new ProductCategory(...).save(flush: true, failOnError: true)
        //new ProductCategory(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //productCategory.id
    }

    void "test get"() {
        setupData()

        expect:
        productCategoryService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ProductCategory> productCategoryList = productCategoryService.list(max: 2, offset: 2)

        then:
        productCategoryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        productCategoryService.count() == 5
    }

    void "test delete"() {
        Long productCategoryId = setupData()

        expect:
        productCategoryService.count() == 5

        when:
        productCategoryService.delete(productCategoryId)
        sessionFactory.currentSession.flush()

        then:
        productCategoryService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ProductCategory productCategory = new ProductCategory()
        productCategoryService.save(productCategory)

        then:
        productCategory.id != null
    }
}
