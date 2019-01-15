package com.lucafaggion
import grails.gorm.transactions.Transactional

@Transactional
class PriceConverterService{

    def storeFromEuro(euro){ 
        println BigDecimal.valueOf(euros).movePointLeft(2)
        return BigDecimal.valueOf(euros).movePointLeft(2) 
    }

    def getEuro(val){
        return BigDecimal.valueOf(new BigDecimal(val)).movePointLeft(2) 
    }

}