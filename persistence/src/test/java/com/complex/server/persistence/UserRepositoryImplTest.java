package com.complex.server.persistence;

import com.complex.server.persistence.domain.Invoice;
import com.complex.server.persistence.domain.PaymentMethod;
import com.complex.server.persistence.domain.User;
import com.complex.server.persistence.repositories.InvoiceRepository;
import com.complex.server.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class UserRepositoryImplTest extends JpaTest {

    @Autowired private InvoiceRepository invoiceRepository;

    @Autowired private UserRepository userRepository;

    @Before
    public void beforeEachTest() {
        userRepository.getAll().forEach(usr -> userRepository.remove(usr));
    }

    @Test
    public void getUserByNameTest() {
        User user = getUserTest(2);

        user.setName("Test");

        userRepository.save(user);
        User userTest = userRepository.getUserByName("Test");
        assertThat(userTest.getName().equals(user.getName()), is(true));
    }

    @Test
    public void getInvoicesFromEmptyUser() {
        User user = getUserTest(2);

        userRepository.save(user);

        assertThat(user.getInvoices().size(), is(0));
    }

    @Test
    public void getInvoicesFromUserWithEmptyList() {
        User user = getUserTest(2);

        userRepository.save(user);
        user.setInvoices(new ArrayList<>());

        assertNotNull(user.getInvoices());
    }

    @Test()
    public void addInvoicesWithoutPersist() {
        Invoice inv = getInvoiceTest(1);
        Invoice inv2 = getInvoiceTest(2);
        User user = getUserTest(2);

        user.setInvoices(Arrays.asList(inv, inv2));

        userRepository.save(user);

        assertNotNull(user.getInvoices());
    }

    @Test
    public void addTwoInvoicesToUserTest() {
        Invoice inv = getInvoiceTest(1);
        Invoice inv2 = getInvoiceTest(2);
        User user = getUserTest(2);

        userRepository.save(user);
        invoiceRepository.save(inv);
        invoiceRepository.save(inv2);


        user.setInvoices(Arrays.asList(inv,inv2));
        assertThat(user.getInvoices().size(), is(2));
    }


    @Test
    public void addInvoiceToUserTest() {
        Invoice inv = getInvoiceTest(1);
        User user = getUserTest(2);

        userRepository.save(user);
        invoiceRepository.save(inv);

        user.setInvoices(Arrays.asList(inv));

        assertThat(user.getInvoices().size(), is(1));
    }

    @Test
    public void addUserToDatabaseTest() {
        User user = getUserTest(1);

        userRepository.save(user);

        assertThat(userRepository.getAll().size(), is(1));
    }

    public Invoice getInvoiceTest(long id) {

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

    private User getUserTest(long id) {
        User user = new User();

        user.setLogin("TestLogin");
        user.setName("Test");
        user.setPassword("TestPassword");
        user.setInvoices(new ArrayList<>());

        return user;
    }

}
