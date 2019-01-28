package com.lucafaggion
import com.lucafaggion.auth.*

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def dipendenteRole = new Role(authority: 'ROLE_DIPENDENTE').save()
        def userRole = new Role(authority: 'ROLE_USER').save()

        def user = new User(username: 'lucafaggion', password: '12345').save()
        UserRole.create user, adminRole
        def user1 = new User(username: 'mariorossi', password: '1234').save()
        UserRole.create user1, userRole
        def user2 = new User(username: 'giuseppebianchi', password: '1234').save()
        UserRole.create user2, dipendenteRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

    }
    def destroy = {
    }
}
