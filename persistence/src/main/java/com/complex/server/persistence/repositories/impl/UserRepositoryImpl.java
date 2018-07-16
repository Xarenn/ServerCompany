package com.complex.server.persistence.repositories.impl;

import com.complex.server.persistence.domain.Invoice;
import com.complex.server.persistence.domain.PaymentMethod;
import com.complex.server.persistence.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserRepositoryImpl {

    private List<User> getAllUsers(List<Long> ids) {
        return Arrays.asList(getUser(ids.get(0)), getUser(ids.get(1)));
    }

    public List<Invoice> getInvoices(long id) {
        List<Invoice> invoices = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Invoice invoice = getInvoice(i);
            invoices.add(invoice);
        }
        return invoices;
    }

    public Invoice getInvoice(long id) {

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

    public User getUser(long id) {
        User user = new User();

        user.setId(id);
        user.setLogin("Loginek");
        user.setName("Imie");
        user.setPassword("tajne");

        return user;
    }

    public List<User> getAdminUsers(String password, List<Long> ids) {
        if (password.equals("pass")) {
            return getAllUsers(ids);
        } else {
            return Collections.emptyList();
        }
    }
}
