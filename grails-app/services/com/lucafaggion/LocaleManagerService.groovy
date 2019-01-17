package com.lucafaggion

import grails.gorm.transactions.Transactional

@Transactional
class LocaleManagerService {

    def setUpIcon(String locale){
        def text = locale.toUpperCase()
        def imgsrc = "/locale/"+ locale + "_flag.png"
        return [localeText:text,localeImg:imgsrc]
    }
}
