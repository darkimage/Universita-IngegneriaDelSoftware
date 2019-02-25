package com.lucafaggion
import com.lucafaggion.auth.*

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN',name:'Admin').save()
        def dipendenteRole = new Role(authority: 'ROLE_DIPENDENTE',name:'Dipendente').save()
        def userRole = new Role(authority: 'ROLE_USER',name:'Cliente').save()


        /*ADMIN*/
        def admin = new User(username: 'lucafaggion', password: '12345', name:'Luca',surname:'Faggion',birthDate: Date.parse('dd-MM-yyy',"04-12-1995"),fiscalCode:'FGGLCU95T04E463P',email:'luca.faggion@gmail.com').save()
        def adminShippingInfo = new ShippingInfo(number:'23',postalcode:'45012',address:'via A. Gramsci',state:'Italia',user:admin)
        adminShippingInfo.save()
        def adminPaymentInfo = new PaymentInfo(user:admin,creditcard:'4420049718813893',cvc:'397',expirationDate:Date.parse('dd-MM-yyy',"01-01-2028"),circuit:'visa')
        adminPaymentInfo.save()
        UserRole.create admin, adminRole

        def user = new User(username: 'mariorossi', password: '1234', name:'Mario',surname:'Rossi',birthDate:Date.parse('dd-MM-yyy',"05-07-1978"),fiscalCode:'RSSMRA78L05A076T',email:'mario.rossi@gmail.com').save()
        def userShippingInfo = new ShippingInfo(number:'45',postalcode:'03010',address:'via Cetona',state:'Italia',user:user)
        userShippingInfo.save()
        def userPaymentInfo = new PaymentInfo(user:user,creditcard:'4666178267427566',cvc:'160',expirationDate:Date.parse('dd-MM-yyy',"01-07-2027"),circuit:'mastercard')
        userPaymentInfo.save()
        UserRole.create user, userRole

        def dipendente = new User(username: 'giuseppebianchi', password: '1234',name:'Giuseppe',surname:'Bianchi',birthDate:Date.parse('dd-MM-yyy',"23-01-1956"),fiscalCode:'BNCGPP56A23A930S',email:'giusepper.bianchi@gmail.com').save()
        def dipendenteShippingInfo = new ShippingInfo(number:'12',postalcode:'05010',address:'Strada Provinciale 189',state:'Italia',user:dipendente)
        dipendenteShippingInfo.save()
        def dipendentePaymentInfo = new PaymentInfo(user:dipendente,creditcard:'4630472569224968',cvc:'387',expirationDate:Date.parse('dd-MM-yyy',"01-10-2024"),circuit:'americanexpress')
        dipendentePaymentInfo.save()
        UserRole.create dipendente, dipendenteRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        //Categoria di default
        if(ProductCategory.list().size() == 0) {
            new ProductCategory(id: 1, name: "No Category").save()
        }
    }

    def destroy = {

    }
}
