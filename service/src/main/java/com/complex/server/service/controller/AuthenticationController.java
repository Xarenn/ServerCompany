package com.complex.server.service.controller;

import com.complex.server.service.security.CustomUserDetailsService;
import com.complex.server.service.security.Exceptions.AuthenticationException;
import com.complex.server.service.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

@RestController @RequestMapping("/api") public class AuthenticationController {

    private String tokenHeader = "Authorization";

    @Autowired private AuthenticationManager authenticationManager;

    @Autowired private JwtTokenUtil jwtTokenUtil;

    @Autowired private CustomUserDetailsService userDetailsService;

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public ResponseEntity<String> createAuthenticationToken(@RequestParam("email") String email,
        @RequestParam("password") String password) throws AuthenticationException {

        System.out.println(email);
        authenticate(email, password);
        if (email.equals("ddd@gmail.com")) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            final String token = jwtTokenUtil.generateToken(userDetails);
            System.out.println(token);
            return new ResponseEntity<String>("Auth " + token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Auth failed", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/auth/refresh", method = RequestMethod.GET)
    public ResponseEntity<String> refreshAndGetAuthenticationToken(HttpServletRequest request) {

        String authToken = request.getHeader(tokenHeader);
        Optional<String> opt = Optional.of(authToken);

        if (authToken.length() > tokenHeader.length()) {

            final String token = authToken.substring(tokenHeader.length());
            String refreshedToken = jwtTokenUtil.refreshToken(token);

            return new ResponseEntity<String>("Auth " + refreshedToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Token not found", HttpStatus.UNAUTHORIZED);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is locked", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials", e);
        }
    }
}
