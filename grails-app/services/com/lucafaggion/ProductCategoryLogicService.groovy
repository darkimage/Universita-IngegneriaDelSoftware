package com.lucafaggion

import grails.gorm.transactions.Transactional

@Transactional
class ProductCategoryLogicService {

    def getCategoryName(Long id) {
        return ProductCategory.read(id).name
    }

    def getCategories(params=[:]){
        def query = "SELECT pc,COUNT(p.id) as productCount FROM ProductCategory as pc LEFT OUTER JOIN Product as p ON pc.id=p.category.id GROUP BY pc.id"
        if(params.sort != null && params.order != null){
            if(params.sort != 'productCount')
            {
                query += " ORDER BY pc."+ params.sort + " " + params.order
            }else{
                query += " ORDER BY " + params.sort + " " + params.order
            }
        }
        def result = ProductCategory.findAll(query,[max:params.max,offset:params.offset])
        def categories = []
        def counts = []
        for (element in result) {
            categories.add(element[0])
            counts.add(element[1])
        }
        return [cat:categories,count:counts]
    }
}
