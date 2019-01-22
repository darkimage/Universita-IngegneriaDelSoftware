package com.lucafaggion
import org.springframework.web.servlet.support.RequestContextUtils
import java.text.*

class PriceFormatterTagLib {
    PriceConverterService priceConverterService
    UtilityService utilityService
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def formatPrice = { attribs ->
        def locale = RequestContextUtils.getLocale(request)
        def value = attribs['value']
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale)
        String moneyString = formatter.format(priceConverterService.convertPrice(value,request))
        out << moneyString
    }

}
