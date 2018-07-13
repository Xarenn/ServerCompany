package com.complex.server.persistence.repositories;

import com.complex.server.persistence.domain.Invoice;

import java.util.List;

public interface InvoiceRepository {

    Invoice get(long id);
    void save(Invoice invoice);
    void remove(long id);
    Invoice getById(long id);
    List<Invoice> getByIds(List<Long> ids);

}
