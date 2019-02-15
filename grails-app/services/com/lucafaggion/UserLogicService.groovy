package com.lucafaggion
import com.lucafaggion.auth.*

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.gorm.transactions.Transactional

@Transactional
class UserLogicService {
    def springSecurityService
    UserService userService
    ShippingInfoService shippingInfoService
    PaymentInfoService paymentInfoService
    UtilityService utilityService
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
        return getUserDatabyUser(user)
    }

    def getUserDatabyUser(User user){
        def userPayment = PaymentInfo.find("FROM PaymentInfo as p WHERE p.user=:user",[user:user])
        def userShipping = ShippingInfo.find("FROM ShippingInfo as s WHERE s.user=:user",[user:user])
        def userOrders = Orders.findAll("FROM Orders as o WHERE o.user=:user AND o.state<>:state",[user:user,state:'cart'])
        def role = Role.find('SELECT r FROM Role as r INNER JOIN UserRole as u ON u.role.id=r.id AND u.user.id=?',[user.id])
        def data = [user:user,userShippingInfo:userShipping,userPaymentInfo:userPayment,userOrders:userOrders,userRole:role]
        return data
    }
    
    def getUserRoles(User user){
        return getUserRolesById(user.id)
    }

    def getUserRolesById(Long id){
        return UserRole.findAll("FROM UserRole as u WHERE u.user.id=:user",[user:id])
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
}
