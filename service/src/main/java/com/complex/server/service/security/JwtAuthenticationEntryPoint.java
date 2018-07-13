package com.complex.server.service.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest arg0, HttpServletResponse resp,
        AuthenticationException arg2) throws IOException, ServletException {

        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}

