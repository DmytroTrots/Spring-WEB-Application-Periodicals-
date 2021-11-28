package com.periodical.trots.entities;

public class Cart {
    private Integer months;

    private PeriodicalEntity periodical;

    private Double totalPrice;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public PeriodicalEntity getPeriodical() {
        return periodical;
    }

    public void setPeriodical(PeriodicalEntity periodical) {
        this.periodical = periodical;
    }

    public Cart() {
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }
}
