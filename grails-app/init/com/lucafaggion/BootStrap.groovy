package com.lucafaggion
import com.lucafaggion.auth.*

class BootStrap {

    // String username
    // String password
    // String name
    // String surname
    // Date birthDate
    // String fiscalCode

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def dipendenteRole = new Role(authority: 'ROLE_DIPENDENTE').save()
        def userRole = new Role(authority: 'ROLE_USER').save()

        def user = new User(username: 'lucafaggion', password: '12345', name:'Luca',surname:'Faggion',birthDate: Date.parse('dd-MM-yyy',"04-12-1995"),fiscalCode:'FGGLCU95T04E463P',email:'luca.faggion@gmail.com').save()
        UserRole.create user, adminRole
        def user1 = new User(username: 'mariorossi', password: '1234', name:'Mario',surname:'Rossi',birthDate:Date.parse('dd-MM-yyy',"05-07-1978"),fiscalCode:'RSSMRA78L05A076T',email:'mario.rossi@gmail.com').save()
        UserRole.create user1, userRole
        def user2 = new User(username: 'giuseppebianchi', password: '1234',name:'Giuseppe',surname:'Bianchi',birthDate:Date.parse('dd-MM-yyy',"23-01-1956"),fiscalCode:'BNCGPP56A23A930S',email:'giusepper.bianchi@gmail.com').save()
        UserRole.create user2, dipendenteRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

    }
    def destroy = {
    }
}
