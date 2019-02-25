package com.lucafaggion

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import grails.buildtestdata.TestDataBuilder
import org.springframework.beans.factory.annotation.Autowired

@Integration
@Rollback
class ProductCategoryLogicServiceSpec extends Specification implements TestDataBuilder{

    @Autowired
    ProductCategoryLogicService service

    def setupData() {
        for(int i=0; i<5; i++){
            ProductCategory.build(name:'testCat'+i)
            for (int j=0; j<10+i; j++){
                Product.build(category:ProductCategory.list()[i])
            }
        }
    }

    void "Test get categories with minimal data"() {
        setupData()

        expect:"fix me"
            def result = service.getCategories()
            result.cat.size() == 6
            result.count.size() == 6
            result.count[0] == 10
    }

    void "Test get categories with data"() {
        setupData()

        expect:"fix me"
        def result = service.getCategories([max:2,offset:2,sort:'name',order:'asc'])
        result.cat.size() == 2
        result.count.size() == 2
        result.count[0] == 12
        result.count[1] == 13
    }
}
