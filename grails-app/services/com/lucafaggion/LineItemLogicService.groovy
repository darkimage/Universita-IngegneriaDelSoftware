package com.lucafaggion

import grails.gorm.transactions.Transactional

@Transactional
class LineItemLogicService {

    def getAllLineItemsOfOrder(order,params){
        return getAllLineItemsOfOrderById(order.id,params)
    }

    def getAllLineItemsOfOrderById(id,params=[:]){
        def query = "FROM LineItem as l WHERE l.orderid.id=:orderid"
        def items
        if(params.sort != null && params.order != null){
            query += " ORDER BY " + params.sort + " " + params.order
            items = LineItem.findAll(query,[orderid:id])
        }else{
            items = LineItem.findAll("FROM LineItem as l WHERE l.orderid.id="+id)
        }
        return items
    }

    def getLineItemOfOrder(id){
        return LineItem.find("FROM LineItem l WHERE l.id = ?",[id])
    }
}
