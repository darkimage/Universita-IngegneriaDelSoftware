package com.lucafaggion

//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.security.core.Authentication
 
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

class ShopSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
        HttpSession session = request.getSession()
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("url_prior_login")
            println redirectUrl
            if (redirectUrl != null) {
                // rimuovi l'attributo dalla sessione
                session.removeAttribute("url_prior_login");
                //ora possiamo reindirizzare l'untente
                getRedirectStrategy().sendRedirect(request, response, redirectUrl)
            } else {
                super.onAuthenticationSuccess(request, response, authentication)
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication)
        }
    }
}