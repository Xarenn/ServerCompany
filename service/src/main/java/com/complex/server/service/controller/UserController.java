package com.complex.server.service.controller;

import com.complex.server.persistence.domain.Invoice;
import com.complex.server.persistence.domain.PaymentMethod;
import com.complex.server.persistence.domain.User;
import com.complex.server.persistence.repositories.impl.UserRepositoryImpl;
import com.complex.server.service.controller.DTO.StatisticsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController @RequestMapping("/api") public class UserController {


    @RequestMapping(value = "/userd", method = RequestMethod.GET)
    public ResponseEntity<User> getUserDatas() {
        return new ResponseEntity<>(getUserTest(0), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET) @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> getUserData(@RequestParam("id") long id) {
        return new ResponseEntity<>(getUserTest(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Invoice>> getInvoices(@RequestParam("id") long id) {
        return new ResponseEntity<>(getInvoicesTest(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET) @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Invoice> getInvoice(@RequestParam("id") long id) {
        return new ResponseEntity<>(getInvoiceTest(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET) @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StatisticsDTO> getStatistics(@RequestParam("id") long id) {
        return new ResponseEntity<>(new StatisticsDTO(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET) @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getUsersAdminData() {
        return new ResponseEntity<>(getAdminUsersTest("dd", Arrays.asList((long) 2, (long) 3)), HttpStatus.OK);
    }

    public Invoice getInvoiceTest(long id) {

        Invoice invoice = new Invoice();
        invoice.setBuyer("BUYER");
        invoice.setBuyerAddress("BUYER ADDRESS");
        invoice.setBuyerNIP("235632463456346");
        invoice.setDateInvoice("DATA WYSTAWIENIA");
        invoice.setDatePayment("DATA PLATNOSCI");
        invoice.setId(id);
        invoice.setPaymentMethod(PaymentMethod.cash);
        invoice.setPhoneNumber("3246324235235");
        invoice.setSeller("SPRZEDAWCA");
        invoice.setSellerNIP("235632463456346");
        invoice.setSellerAddress("ADDRESS SPRZEDAWCY");
        invoice.setUserId(id);
        invoice.setValue(id);

        return invoice;
    }

    private User getUserTest(long id) {
        User user = new User();

        user.setId(id);
        user.setLogin("Loginek");
        user.setName("Imie");
        user.setPassword("tajne");

        return user;
    }

    private List<User> getAdminUsersTest(String password, List<Long> ids) {
        if (password.equals("pass")) {
            return getAllUsersTest(ids);
        } else {
            return Collections.emptyList();
        }
    }

    private List<User> getAllUsersTest(List<Long> ids) {
        return Arrays.asList(getUserTest(ids.get(0)), getUserTest(ids.get(1)));
    }

    private List<Invoice> getInvoicesTest(long id) {
        List<Invoice> invoices = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Invoice invoice = getInvoiceTest(i);
            invoices.add(invoice);
        }
        return invoices;
    }


}
