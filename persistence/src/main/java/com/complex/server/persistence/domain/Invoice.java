package com.complex.server.persistence.domain;


import javax.persistence.*;

@Entity @Table(name = "Invoices") public class Invoice {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false) private long id;

    @Column(name = "UserId") private long userId;

    @Column(name = "Value") private double value;

    @Column(name = "Seller") private String seller;

    @Column(name = "SellerAddress") private String sellerAddress;

    @Column(name = "SellerNIP") private String sellerNIP;

    @Column(name = "PhoneNumber") private String phoneNumber;

    @Column(name = "Buyer") private String buyer;

    @Column(name = "BuyerAddress") private String buyerAddress;

    @Column(name = "BuyerNIP") private String buyerNIP;

    @Column(name = "DateInvoice") private String dateInvoice;

    @Column(name = "DatePayment") private String datePayment;

    @Column(name = "PaymentMethod") private PaymentMethod paymentMethod;

    public Invoice() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getSeller() { return seller; }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerNIP() {
        return sellerNIP;
    }

    public void setSellerNIP(String sellerNIP) {
        this.sellerNIP = sellerNIP;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBuyer() { return buyer; }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerNIP() {
        return buyerNIP;
    }

    public void setBuyerNIP(String buyerNIP) {
        this.buyerNIP = buyerNIP;
    }

    public String getDateInvoice() {
        return dateInvoice;
    }

    public void setDateInvoice(String dateInvoice) { this.dateInvoice = dateInvoice; }

    public String getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(String datePayment) {
        this.datePayment = datePayment;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override public String toString() {
        return "User: " + this.id;
    }
}
