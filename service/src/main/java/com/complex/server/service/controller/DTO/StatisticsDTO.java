package com.complex.server.service.controller.DTO;

import com.complex.server.persistence.domain.Invoice;
import com.complex.server.service.controller.Statistics;

import java.io.Serializable;

public class StatisticsDTO implements Serializable {

    private double tax;

    private double vat;

    private int quantity;

    private Invoice lastAdded;

    public StatisticsDTO(long userId) {
        Statistics statistics = new Statistics(userId);

        this.lastAdded = statistics.getLastAdded();
        this.tax = statistics.getTax("2018");
        this.vat = statistics.getVat("2018");
        this.quantity = statistics.getQuantity();
    }

    public double getTax() {
        return tax;
    }

    public double getVat() {
        return vat;
    }

    public int getQuantity() {
        return quantity;
    }

    public Invoice getLastAdded() {
        return lastAdded;
    }
}
