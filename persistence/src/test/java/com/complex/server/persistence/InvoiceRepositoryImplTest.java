package com.complex.server.persistence;

import com.complex.server.persistence.domain.Invoice;
import com.complex.server.persistence.domain.PaymentMethod;
import com.complex.server.persistence.repositories.InvoiceRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class InvoiceRepositoryImplTest extends JpaTest {

    @Autowired
    private InvoiceRepository repository;

    @Test public void getInvoiceFromDatabaseTest() {
        Invoice inv = makeInvoiceForTest(1);

        repository.save(inv);

        assertEquals(repository.get(inv.getId()), inv);
    }

    @Test public void addInvoiceToDatabaseTest() {
        Invoice inv = makeInvoiceForTest(2);

        repository.save(inv);

        assertEquals(repository.get(inv.getId()).getUserId(), inv.getUserId());
    }

    private Invoice makeInvoiceForTest(long id) {
        Invoice invoice = new Invoice();
        invoice.setBuyer("BUYER");
        invoice.setBuyerAddress("BUYER ADDRESS");
        invoice.setBuyerNIP("235632463456346");
        invoice.setDateInvoice("DATA WYSTAWIENIA");
        invoice.setDatePayment("DATA PLATNOSCI");
        invoice.setPaymentMethod(PaymentMethod.cash);
        invoice.setPhoneNumber("3246324235235");
        invoice.setSeller("SPRZEDAWCA");
        invoice.setSellerNIP("235632463456346");
        invoice.setSellerAddress("ADDRESS SPRZEDAWCY");
        invoice.setUserId(id);
        invoice.setValue(id);

        return invoice;
    }

}
