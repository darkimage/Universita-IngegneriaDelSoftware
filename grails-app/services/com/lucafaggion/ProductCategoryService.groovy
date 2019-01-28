package com.lucafaggion

import grails.gorm.transactions.Transactional

@Transactional
class ProductCategoryService {

    def getCategoryName(Integer id) {
        return ProductCategory.read(id).name
    }
}
