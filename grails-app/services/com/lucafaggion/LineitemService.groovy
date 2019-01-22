package com.lucafaggion
import grails.gorm.services.Service

@Service(LineItem)
interface LineitemService {

    LineItem get(Serializable id)

    List<LineItem> list(Map args)

    Long count()

    void delete(Serializable id)

    LineItem save(LineItem lineitem)
   
}