package com.complex.server.service.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    List<GrantedAuthority> list = new ArrayList<>();
    list.add(new SimpleGrantedAuthority("ROLE_USER"));

    return new LoginUser(name, bCryptPasswordEncoder.encode("password"), list);
  }

  public UserDetails loadUserByToken(String username, String token)
      throws UsernameNotFoundException {
    List<GrantedAuthority> list = new ArrayList<>();
    list.add(new SimpleGrantedAuthority("ROLE_USER"));

    return new LoginUser(username, bCryptPasswordEncoder.encode("password"), list);
  }

}
