package com.lucafaggion
import grails.plugin.springsecurity.annotation.Secured

@Secured(value="hasAnyRole('ROLE_ADMIN','ROLE_DIPENDENTE')")
class ControlPanelController {
    def index = { }

    def manageUsers = {
        redirect(controller:'user',action:'index')
    }

    def manageProducts = {
        redirect(controller:'product',action:'listProductCompact')
    }

    def manageOrders = {
        redirect(controller:'orders',action:'index')
    }
    
}
