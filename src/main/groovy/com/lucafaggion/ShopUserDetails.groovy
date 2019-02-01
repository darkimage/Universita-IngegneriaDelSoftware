package com.lucafaggion

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority


class ShopUserDetails extends GrailsUser {

   final String name
   final String surname
   final Date birthdate

   ShopUserDetails(String username, String password, boolean enabled,
                 boolean accountNonExpired, boolean credentialsNonExpired,
                 boolean accountNonLocked,
                 Collection<GrantedAuthority> authorities,
                 long id, String name,String surname,Date birthdate) {
      super(username, password, enabled, accountNonExpired,
            credentialsNonExpired, accountNonLocked, authorities, id)

      this.name = name
      this.surname = surname
      this.birthdate = birthdate
   }
}