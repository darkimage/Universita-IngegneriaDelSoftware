import com.lucafaggion.auth.UserPasswordEncoderListener
import com.lucafaggion.ControllerException
import com.lucafaggion.ShopUserDetailsService
import grails.plugin.springsecurity.SpringSecurityUtils
import com.lucafaggion.ShopSuccessHandler

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    controllerException(ControllerException){}
    userDetailsService(ShopUserDetailsService)
    authenticationSuccessHandler(ShopSuccessHandler)
}
