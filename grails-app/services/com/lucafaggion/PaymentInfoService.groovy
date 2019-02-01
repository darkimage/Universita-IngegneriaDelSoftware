package com.lucafaggion
import grails.gorm.services.Service

@Service(PaymentInfo)
interface PaymentInfoService {

    PaymentInfo get(Serializable id)

    List<PaymentInfo> list(Map args)

    Long count()

    void delete(PaymentInfo id)

    PaymentInfo save(PaymentInfo product)
  
}