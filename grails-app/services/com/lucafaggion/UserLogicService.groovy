package com.lucafaggion
import com.lucafaggion.auth.*

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.gorm.transactions.Transactional

@Transactional
class UserLogicService {
    def springSecurityService
    UserService userService
    OrderslogicService orderslogicService

    def createUserRole(user,userRole){
        if(SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')){
            UserRole.create(user,userRole,true)
        }else{
            UserRole.create(user,Role.find("FROM Role as r WHERE r.authority=:role",[role:'ROLE_USER']),true)
        }
    }

    def getUserDatabyId(Long id){
        def user = userService.get(id)
        if(user != null) {
            return getUserDatabyUser(user)
        }else{
            null
        }
    }

    def getUserDatabyUser(User user){
        if(user != null) {
            def userPayment = PaymentInfo.find("FROM PaymentInfo as p WHERE p.user=:user AND p.version = (select max(pp.version) from PaymentInfo pp where pp.user=p.user)", [user: user])
            def userShipping = ShippingInfo.find("FROM ShippingInfo as s WHERE s.user=:user AND s.version = (select max(ss.version) from ShippingInfo ss where ss.user=s.user)", [user: user])
            def userOrders = Orders.findAll("FROM Orders as o WHERE o.user=:user AND o.state<>:state", [user: user, state: 'cart'])
            def role = Role.find('SELECT r FROM Role as r INNER JOIN UserRole as u ON u.role.id=r.id AND u.user.id=?', [user.id])
            def data = [user: user, userShippingInfo: userShipping, userPaymentInfo: userPayment, userOrders: userOrders, userRole: role]
            return data
        }else{
            null
        }
    }

    def getUserRolesById(Long id){
        def user = userService.get(id)
        if(user != null) {
            return getUserRoles(user)
        }else{
            return null
        }
    }


    def getUserRoles(User user){
        if(user != null) {
            return UserRole.findAll("FROM UserRole as u WHERE u.user=:user", [user: user])
        }else{
            return null
        }
    }

    def deleteUserRoles(User user) {
        deleteUserRolesById(user.id)
    }

    def deleteUserRolesById(Long id){
        def roles = getUserRolesById(id)
        for (role in roles) {
            role.delete()
        }
    }

    def deleteUserOrders(User user){
        deleteUserOrdersById(user.id)
    }

    def deleteUserOrdersById(Long id){
        def userOrders = Orders.findAll("FROM Orders as o WHERE o.user.id=:user",[user:id])
        for (order in userOrders) {
            orderslogicService.deleteOrder(order.id)
        }
    }

    def deleteUserDetailsById(Long id){
        def paymentInfos = PaymentInfo.findAll("FROM PaymentInfo as p WHERE p.user.id=:id",[id:id])
        paymentInfos.each { item ->
            item.delete(flush: true)
        }
        def shippingInfos = ShippingInfo.findAll("FROM ShippingInfo as s WHERE s.user.id=:id",[id:id])
        shippingInfos.each { item ->
            item.delete(flush: true)
        }
    }
}
