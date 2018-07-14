package com.complex.server.persistence.repositories.impl;

import com.complex.server.persistence.domain.Invoice;
import com.complex.server.persistence.domain.PaymentMethod;
import com.complex.server.persistence.repositories.InvoiceRepository;
import com.complex.server.persistence.repositories.SimpleRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository @Transactional public class InvoiceRepositoryImpl extends SimpleRepositoryImpl<Invoice>
    implements InvoiceRepository {

    @PersistenceContext private EntityManager entityManager;

    @Override public Invoice get(long index) {
        return super.get(Invoice.class, index);
    }

    @Override public void save(Invoice invoice) {
        super.save(invoice);
    }

    @Override public void remove(Invoice invoice) {
        super.remove(invoice);
    }

    @Override public Invoice getById(long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteriaQuery = criteriaBuilder.createQuery(Invoice.class);
        Root<Invoice> root = criteriaQuery.from(Invoice.class);

        criteriaQuery.select(root).where(criteriaBuilder.gt(root.get("id"), id));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override public List<Invoice> getByIds(List<Long> ids) {

        Query query = entityManager
            .createQuery("from " + Invoice.class.getSimpleName() + " item where item.id in :ids");
        query.setParameter("ids", ids);

        return query.getResultList();
    }

    public Invoice getMostExpensiveInvoice(List<Long> ids) {
        List<Invoice> invoices = getByIds(ids);

        return invoices.stream().max(Comparator.comparing(Invoice::getValue))
            .orElseThrow(NoSuchElementException::new);
    }

    public List<Invoice> getInvoicesPayedBySpecifyType(PaymentMethod paymentMethod, long userid) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Invoice> criteriaQuery = criteriaBuilder.createQuery(Invoice.class);
        Root<Invoice> root = criteriaQuery.from(Invoice.class);

        criteriaQuery.select(root).where(criteriaBuilder.gt(root.get("Userid"), userid));

        List<Invoice> invoices = entityManager.createQuery(criteriaQuery).getResultList();

        return invoices.stream().filter(inv -> inv.getPaymentMethod().equals(paymentMethod))
            .collect(Collectors.toList());
    }

}
