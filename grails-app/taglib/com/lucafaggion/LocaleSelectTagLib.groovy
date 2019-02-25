package com.lucafaggion
import org.springframework.web.servlet.support.RequestContextUtils

class LocaleSelectTagLib {
    LocaleManagerService localeManagerService
    UtilityService utilityService
    static defaultEncodeAs = [taglib:'none']
    //static encodeAsForTags = [localeLink: [taglib:'html']]

    def localeIcon = { attribs ->
        def withText =  Boolean.valueOf(attribs['withText'])
        def locale = attribs['locale']
        def setup = localeManagerService.setUpIcon(locale)
        out << g.render(template:'/templates/localeIcon',model:[localeImg:setup.localeImg,withText:withText,localeText:setup.localeText])
    }

    def currentLocaleIcon = { attribs ->
        def withText =  Boolean.valueOf(attribs['withText'])
        def locale = RequestContextUtils.getLocale(request)
        def setup = localeManagerService.setUpIcon(locale.getLanguage())
        out << g.render(template:'/templates/localeIcon',model:[localeImg:setup.localeImg,withText:withText,localeText:setup.localeText])
    }

    def localeLink = { attribs, body ->
        def locale = attribs['locale']
        def region = attribs['region']
        params.lang = locale + "_" + region
        out << g.render(template:'/templates/localeLink',model:[params:params,controllerName:controllerName,actionName:actionName,text:locale.toUpperCase(),body:body()])
    }
}
