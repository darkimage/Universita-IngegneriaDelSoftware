package com.lucafaggion
import org.springframework.security.access.annotation.Secured

@Secured(value=["isFullyAuthenticated()"])
class ProfileController {

    def index = {

    }
    
}
