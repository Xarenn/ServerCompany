package com.complex.server.persistence.repositories.specification;

import com.complex.server.persistence.domain.Invoice;
import com.complex.server.persistence.repositories.AbstractSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class IsGreaterAmount extends AbstractSpecification<Invoice> {

    public Predicate toPredicate(Root<Invoice> invoiceRoot, CriteriaBuilder cb, long amount) {
        return cb.and(
            cb.greaterThan(invoiceRoot.get("value"), amount)
        );
    }

}
