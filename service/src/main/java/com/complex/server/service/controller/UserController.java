package com.complex.server.service.controller;

import com.complex.server.persistence.domain.Invoice;
import com.complex.server.persistence.domain.User;
import com.complex.server.persistence.repositories.impl.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

  UserRepositoryImpl userRepository = new UserRepositoryImpl();

  @RequestMapping(value = "/userd", method = RequestMethod.GET)
  public ResponseEntity<User> getUserDatas() {
    return new ResponseEntity<User>(userRepository.getUser(0), HttpStatus.OK);
  }

  @RequestMapping(value = "/user", method = RequestMethod.GET)
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<User> getUserData(@RequestParam("id") long id) {
    return new ResponseEntity<User>(userRepository.getUser(id), HttpStatus.OK);
  }

  @RequestMapping(value = "/invoices", method = RequestMethod.GET)
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<List<Invoice>> getInvoices(@RequestParam("id") long id) {
    return new ResponseEntity<List<Invoice>>(userRepository.getInvoices(id), HttpStatus.OK);
  }

  @RequestMapping(value = "/invoice", method = RequestMethod.GET)
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Invoice> getInvoice(@RequestParam("id") long id) {
    return new ResponseEntity<Invoice>(userRepository.getInvoice(id), HttpStatus.OK);
  }

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<User>> getUsersAdminData() {
    return new ResponseEntity<List<User>>(userRepository.getAdminUsers("dd", Arrays.asList((long)2,(long)3)), HttpStatus.OK);
  }
}
