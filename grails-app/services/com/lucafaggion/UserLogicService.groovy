package com.lucafaggion
import com.lucafaggion.auth.*

import grails.gorm.transactions.Transactional

@Transactional
class UserLogicService {
    UserService userService
    ShippingInfoService shippingInfoService
    PaymentInfoService paymentInfoService
    UtilityService utilityService
    
    def getUserDatabyId(Long id){
        def user = userService.get(id)
        return getUserDatabyUser(user)
    }

    def getUserDatabyUser(User user){
        def userPayment = PaymentInfo.find("FROM PaymentInfo as p WHERE p.user=:user",[user:user])
        def userShipping = ShippingInfo.find("FROM ShippingInfo as s WHERE s.user=:user",[user:user])
        def userOrders = Orders.findAll("FROM Orders as o WHERE o.user=:user AND o.state<>:state",[user:user,state:'cart'])
        def data = [user:user,userShippingInfo:userShipping,userPaymentInfo:userPayment,userOrders:userOrders]
        return data
    }
}
