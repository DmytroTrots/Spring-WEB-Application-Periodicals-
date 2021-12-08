package com.periodical.trots.entities;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * The type Periodical has receipt entity id.
 */
@Embeddable
public class PeriodicalHasReceiptEntityId implements Serializable {

    @Column(name = "periodical_sell_id", nullable = false)
    private Integer periodicalSellId;

    @Column(name = "receipt_id", nullable = false)
    private Integer receiptId;

    /**
     * Gets receipt id.
     *
     * @return the receipt id
     */
    public Integer getReceiptId() {
        return receiptId;
    }

    /**
     * Sets receipt id.
     *
     * @param receiptId the receipt id
     */
    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    /**
     * Gets periodical sell id.
     *
     * @return the periodical sell id
     */
    public Integer getPeriodicalSellId() {
        return periodicalSellId;
    }

    /**
     * Sets periodical sell id.
     *
     * @param periodicalSellId the periodical sell id
     */
    public void setPeriodicalSellId(Integer periodicalSellId) {
        this.periodicalSellId = periodicalSellId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiptId, periodicalSellId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PeriodicalHasReceiptEntityId entity = (PeriodicalHasReceiptEntityId) o;
        return Objects.equals(this.receiptId, entity.receiptId) && Objects.equals(this.periodicalSellId, entity.periodicalSellId);
    }
}