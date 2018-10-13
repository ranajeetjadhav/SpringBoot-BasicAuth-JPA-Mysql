/**
 * 
 */
package com.example.easynotes.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author e1077874
 *
 */
@Component
public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
      throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm=" +getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        System.out.println("HTTP Status 401 - " + authEx.getMessage());
    }

	@Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("DeveloperStack");
        super.afterPropertiesSet();
    }
}
