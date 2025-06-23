package cl.bibliosmart.bibliosmart.modules.login.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority auth : authorities) {
            String role = auth.getAuthority(); // Esto será: "ROLE_ADMINISTRADOR", etc.

            // Comprobamos con contains
            if (role.contains("ADMINISTRADOR")) {
                response.sendRedirect("/admin");
                return;
            } else if (role.contains("BIBLIOTECARIO")) {
                response.sendRedirect("/bibliotecario");
                return;
            } else if (role.contains("LECTOR")) {
                response.sendRedirect("/catalogo");
                return;
            }
        }

        // Si no tiene rol válido
        response.sendRedirect("/login?error");
    }
}
