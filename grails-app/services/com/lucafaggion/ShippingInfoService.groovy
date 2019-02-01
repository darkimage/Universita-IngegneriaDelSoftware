package com.lucafaggion
import grails.gorm.services.Service

@Service(ShippingInfo)
interface ShippingInfoService {

    ShippingInfo get(Serializable id)

    List<ShippingInfo> list(Map args)

    Long count()

    void delete(Serializable id)

    ShippingInfo save(ShippingInfo product)
  
}