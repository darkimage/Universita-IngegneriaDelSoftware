package com.lucafaggion
import grails.gorm.services.Service

@Service(ProductCategory)
interface ProductCategoryService {

    ProductCategory get(Serializable id)

    List<ProductCategory> list(Map args)

    Long count()

    void delete(Serializable id)

    ProductCategory save(ProductCategory order)
  
}