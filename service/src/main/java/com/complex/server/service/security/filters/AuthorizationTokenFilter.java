package com.complex.server.service.security.filters;

import com.complex.server.service.security.Exceptions.ExceptionsCode;
import com.complex.server.service.security.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthorizationTokenFilter extends OncePerRequestFilter {

  private UserDetailsService userDetailsService;

  private JwtTokenUtil jwtTokenUtil;

  private String tokenHeader;

  public AuthorizationTokenFilter(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil,
      String tokenHeader) {
    this.userDetailsService = userDetailsService;
    this.jwtTokenUtil = jwtTokenUtil;
    this.tokenHeader = tokenHeader;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {

    final String requestHeader = request.getHeader(this.tokenHeader);
    final String bearer = "Auth";
    final String errorHeader = "error";

    String username = null;
    String authToken = null;
    if (requestHeader != null && requestHeader.startsWith(bearer)) {
      authToken = requestHeader.substring(bearer.length());
      try {
        username = jwtTokenUtil.getUsernameFromToken(authToken);
      } catch (IllegalArgumentException e) {
        response.addHeader(errorHeader, ExceptionsCode.UsernameTokenExcepction);
      } catch (ExpiredJwtException e) {
        response.addHeader(errorHeader, ExceptionsCode.JWTExpiredExceptionCode);
      }
    } else {
      logger.warn("couldn't find Auth string, will ignore the header");
      response.addHeader(errorHeader, ExceptionsCode.TokenNotFound);
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      //Checking user by details from token-> not db
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

      if (jwtTokenUtil.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    chain.doFilter(request, response);
  }

}
