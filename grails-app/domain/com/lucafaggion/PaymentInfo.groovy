package com.lucafaggion
import com.lucafaggion.auth.*

class PaymentInfo {
    int creditcart
    String circuit
    int cvc
    Date expirationDate
    static belongsTo = [user:User]
    static constraints = {
    }
}
