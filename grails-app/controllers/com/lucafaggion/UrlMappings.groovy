package com.lucafaggion

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:'product',action:'index') //( controller:'Item', action:'index' )
        "/register"(controller:'User', action:'create')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
