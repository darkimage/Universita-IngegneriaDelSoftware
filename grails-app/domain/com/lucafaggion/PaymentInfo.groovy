package com.lucafaggion
import com.lucafaggion.auth.*

class PaymentInfo {
    String creditcard
    String circuit
    int cvc
    Date expirationDate
    static belongsTo = [user:User]
    static constraints = {
    }
}
