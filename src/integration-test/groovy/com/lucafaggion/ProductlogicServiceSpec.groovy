package com.lucafaggion

import com.lucafaggion.auth.*
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import grails.buildtestdata.TestDataBuilder
import org.springframework.beans.factory.annotation.Autowired
import grails.plugin.springsecurity.SpringSecurityUtils

@Integration
@Rollback
class ProductlogicServiceSpec extends Specification implements TestDataBuilder{

    @Autowired
    ProductlogicService service

    def setupData() {
        def cat1 = ProductCategory.build()
        def cat2 = ProductCategory.build()
        //Normal Products
        for (int i = 0; i < 3; i++) {
            Product.build(name:"Product"+i,price:2+i*2,quantity:20+i,category:cat1)
        }
        //Featured products
        for (int i = 0; i < 2; i++) {
            Product.build(price:2+i*3,quantity:50+i*2,featured:true,category:cat2)
        }
    }

    void "Test getfeaturedProducts"() {
        given:
        setupData()

        when:
        def featuredProducts = service.getfeaturedProducts()

        then:"The correct product list is returned"
        featuredProducts.size() == 2
        featuredProducts[0].featured
        featuredProducts[1].featured

    }

    void "Test getProductData with data"(){
        given:
        setupData()

        when:
        def data = service.getProductData([cat:ProductCategory.list()[1].id])

        then:"The correct data is returned"
        data.cat.size() == 3
        data.list.size() == 3
        data.count == 3
    }

    void "Test getProductData with invalid data"(){
        given:
        setupData()

        when:
        def data = service.getProductData([cat:0])

        then:"The data is correct"
        !data.list
    }

    void "Test search with valid string"(){
        given:
        setupData()

        when:
        def data = service.getProductsFromSearch([value:"Product"])

        then:"The correct data is returned"
        data.list.size() == 3
        data.count == 3
    }

    void "Test search with an invalid string"(){
        given:
        setupData()

        when:
        def data = service.getProductsFromSearch([value:"RandomRandom"])

        then:"The correct data is returned"
        !data.list
    }

    void "Test getProductsOfCategory with valid data"(){
        given:
        setupData()

        when:
        def data = service.getProductsOfCategory(ProductCategory.list()[1].id,[max:2])

        then:"The correct data is returned"
        data.size() == 2
    }

    void "Test getProductsOfCategory with invalid data"(){
        given:
        setupData()

        when:
        def data = service.getProductsOfCategory(0,[max:2])

        then:"The correct data is returned"
        !data
    }

    void "Test getCategoryProductCount with valid data"(){
        given:
        setupData()

        when:
        def count = service.getCategoryProductCount(ProductCategory.list()[1].id)

        then:"The correct data is returned"
        count == 3
    }

    void "Test getCategoryProductCount with invalid data"(){
        given:
        setupData()

        when:
        def count = service.getCategoryProductCount(0)

        then:"The correct data is returned"
        !count
    }

    void "Test generateIdentifier"(){
        given:
        setupData()
        def product = Product.list()[0]

        when:
        service.generateIdentifier(product)

        then:
        product.identifier.size() == 3 + String.valueOf(product.id).length() + 3
    }
}
