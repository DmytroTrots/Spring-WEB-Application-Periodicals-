package com.periodical.trots.entities;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The type Periodical has receipt entity.
 */
@Table(name = "periodical_has_receipt", indexes = {@Index(name = "fk_periodical_has_receipt_periodical1_idx", columnList = "periodical_sell_id"), @Index(name = "fk_periodical_has_receipt_receipt1_idx", columnList = "receipt_id")})
@Entity
public class PeriodicalHasReceiptEntity {
    @EmbeddedId
    private PeriodicalHasReceiptEntityId id;

    @Column(name = "price_per_month", nullable = false, precision = 4, scale = 2)
    private BigDecimal pricePerMonth;

    @Column(name = "number_of_month", nullable = false, length = 45)
    private Integer numberOfMonth;

    /**
     * The M receipt.
     */
    @ManyToOne
    @MapsId("receiptId")
    @JoinColumn(name = "receipt_id")
    ReceiptEntity mReceipt;

    /**
     * The Periodical.
     */
    @ManyToOne
    @MapsId("periodicalSellId")
    @JoinColumn(name = "periodical_sell_id")
    PeriodicalEntity periodical;

    /**
     * Gets receipt.
     *
     * @return the receipt
     */
    public ReceiptEntity getmReceipt() {
        return mReceipt;
    }

    /**
     * Sets receipt.
     *
     * @param mReceipt the m receipt
     */
    public void setmReceipt(ReceiptEntity mReceipt) {
        this.mReceipt = mReceipt;
    }

    /**
     * Gets periodical.
     *
     * @return the periodical
     */
    public PeriodicalEntity getPeriodical() {
        return periodical;
    }

    /**
     * Sets periodical.
     *
     * @param periodical the periodical
     */
    public void setPeriodical(PeriodicalEntity periodical) {
        this.periodical = periodical;
    }

    /**
     * Gets number of month.
     *
     * @return the number of month
     */
    public Integer getNumberOfMonth() {
        return numberOfMonth;
    }

    /**
     * Sets number of month.
     *
     * @param numberOfMonth the number of month
     */
    public void setNumberOfMonth(Integer numberOfMonth) {
        this.numberOfMonth = numberOfMonth;
    }

    /**
     * Gets price per month.
     *
     * @return the price per month
     */
    public BigDecimal getPricePerMonth() {
        return pricePerMonth;
    }

    /**
     * Sets price per month.
     *
     * @param pricePerMonth the price per month
     */
    public void setPricePerMonth(BigDecimal pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public PeriodicalHasReceiptEntityId getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(PeriodicalHasReceiptEntityId id) {
        this.id = id;
    }
}