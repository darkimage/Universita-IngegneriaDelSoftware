package com.lucafaggion
import grails.gorm.transactions.Transactional
import org.springframework.web.servlet.support.RequestContextUtils

//@Transactional
class PriceConverterService{ 
    static euroDollar = BigDecimal.valueOf(1.1367)
    static dollarEuro = BigDecimal.valueOf(0.88)

    def convertPriceToStore(price,request){ 
        def locale = RequestContextUtils.getLocale(request)
        def money = (new BigDecimal(price)).movePointRight(2)
        println money
        if(locale.language == "en"){
            return toEuro(money).setScale(0,BigDecimal.ROUND_DOWN).intValueExact()
        }else{
            return money.setScale(0,BigDecimal.ROUND_DOWN).intValueExact()
        }
    }

    def convertPrice(price,request){
        def locale = RequestContextUtils.getLocale(request)
        def money = BigDecimal.valueOf(price).movePointLeft(2)
        if(locale.language == "it"){
            return money
        }else{
            return toDollar(money).setScale(2, BigDecimal.ROUND_DOWN)
        }
    }

    private BigDecimal toDollar(BigDecimal money){
        return money * euroDollar
    }

    private BigDecimal toEuro(BigDecimal money) {
        return money * dollarEuro
    }

}