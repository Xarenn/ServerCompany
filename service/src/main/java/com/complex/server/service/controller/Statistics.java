package com.complex.server.service.controller;

import com.complex.server.persistence.domain.Invoice;
import com.complex.server.persistence.repositories.impl.UserRepositoryImpl;

public class Statistics {

    UserRepositoryImpl userRepository = new UserRepositoryImpl();

    private double tax;

    private double vat;

    private int quantity;

    private Invoice lastAdded;

    long userId;

    public Statistics(long userId) {
        this.userId = userId;
    }

    public double getTax(String year) {
        this.getAllTaxByYear(year);
        return tax;
    }

    public double getVat(String year) {
        this.getAllVatByYear(year);
        return vat;
    }

    public int getQuantity() {
        this.getQuantityInvoices();
        return quantity;
    }

    public Invoice getLastAdded() {
        this.getLastAddedInvoice(this.userId);
        return lastAdded;
    }

    private void setTax(double tax) {
        this.tax = tax;
    }

    private void setVat(double vat) {
        this.vat = vat;
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private void setLastAdded(Invoice lastAdded) {
        this.lastAdded = lastAdded;
    }

    private void getLastAddedInvoice(long userId) {
        setLastAdded(userRepository.getInvoice(userId));
    }

    private void getQuantityInvoices() {
        setQuantity(userRepository.getInvoices(this.userId).size());
    }

    private void getVatByMonth(String year, String month) {
        setVat(335.32);
    }

    private void getAllVatByYear(String year) {
        setVat(122.23);
    }

    private void getTaxByMonth(String year, String month) {
        setTax(542.32);
    }

    private void getAllTaxByYear(String year) {
        setTax(643.23);
    }
}
