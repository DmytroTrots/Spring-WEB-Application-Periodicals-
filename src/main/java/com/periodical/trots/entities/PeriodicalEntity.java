package com.periodical.trots.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * The type Periodical entity.
 */
@Table(name = "periodical", indexes = {@Index(name = "idx_periodical_title", columnList = "title"), @Index(name = "fk_periodical_publisher1_idx", columnList = "publisher_id")}, uniqueConstraints = {@UniqueConstraint(name = "title_UNIQUE", columnNames = {"title"})})
@Entity
public class PeriodicalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sell_id", nullable = false)
    private Integer sellId;

    @Column(name = "title", nullable = false, unique = true, length = 125)
    @Pattern(regexp = "[A-Za-z0-9_ ]{2,64}", message = "{error.title}")
    private String title;

    @NotNull(message = "{error.numberOfPages}")
    @Positive(message = "{error.numberOfPages}")
    @Min(message = "{error.numberOfPages}", value = 1)
    @Max(value = 1000, message = "{error.numberOfPages}")
    @Column(name = "number_of_pages", nullable = false)
    private Integer numberOfPages;

    @Column(name = "periodicity_per_year", nullable = false)
    private Integer periodicityPerYear;

    @NotNull(message = "{error.percentageOfAdvertising}")
    @Positive(message = "{error.percentageOfAdvertising}")
    @Min(message = "{error.percentageOfAdvertising}", value = 0)
    @Max(value = 100, message = "{error.percentageOfAdvertising}")
    @Column(name = "percentage_of_advertising", nullable = false)
    private Integer percentageOfAdvertising;

    @NotNull(message = "{error.pricePerMonth}")
    @Positive(message = "{error.pricePerMonth}")
    @Min(message = "{error.pricePerMonth}", value = 1)
    @Column(name = "price_per_month", nullable = false)
    private BigDecimal pricePerMonth;

    @Length(message = "{error.description}", min = 5, max = 1000)
    @NotBlank(message = "{error.description}")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "{error.rating}")
    @Max(message = "{error.rating}", value = 5)
    @Min(message = "{error.rating}", value = 0)
    @Column(name = "rating")
    private Double rating;

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    private PublisherEntity publisher;

    @Column(name = "images", nullable = false, length = 500)
    private String images;

    /**
     * The Receipt entities.
     */
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "periodical")
    Set<PeriodicalHasReceiptEntity> receiptEntities;

    /**
     * The Periodical has subject.
     */
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "periodical")
    Set<PeriodicalHasSubjectEntity> periodicalHasSubject;

    /**
     * Instantiates a new Periodical entity.
     *
     * @param sellId the sell id
     */
    public PeriodicalEntity(Integer sellId) {
        this.sellId = sellId;
    }

    /**
     * Instantiates a new Periodical entity.
     */
    public PeriodicalEntity() {
    }

    /**
     * Gets receipt entities.
     *
     * @return the receipt entities
     */
    public Set<PeriodicalHasReceiptEntity> getReceiptEntities() {
        return receiptEntities;
    }

    /**
     * Sets receipt entities.
     *
     * @param receiptEntities the receipt entities
     */
    public void setReceiptEntities(Set<PeriodicalHasReceiptEntity> receiptEntities) {
        this.receiptEntities = receiptEntities;
    }

    /**
     * Gets periodical has subject.
     *
     * @return the periodical has subject
     */
    public Set<PeriodicalHasSubjectEntity> getPeriodicalHasSubject() {
        return periodicalHasSubject;
    }

    /**
     * Sets periodical has subject.
     *
     * @param periodicalHasSubject the periodical has subject
     */
    public void setPeriodicalHasSubject(Set<PeriodicalHasSubjectEntity> periodicalHasSubject) {
        this.periodicalHasSubject = periodicalHasSubject;
    }

    /**
     * Gets images.
     *
     * @return the images
     */
    public String getImages() {
        return images;
    }

    /**
     * Sets images.
     *
     * @param images the images
     */
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * Gets publisher.
     *
     * @return the publisher
     */
    public PublisherEntity getPublisher() {
        return publisher;
    }

    /**
     * Sets publisher.
     *
     * @param publisher the publisher
     */
    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * Gets percentage of advertising.
     *
     * @return the percentage of advertising
     */
    public Integer getPercentageOfAdvertising() {
        return percentageOfAdvertising;
    }

    /**
     * Sets percentage of advertising.
     *
     * @param percentageOfAdvertising the percentage of advertising
     */
    public void setPercentageOfAdvertising(Integer percentageOfAdvertising) {
        this.percentageOfAdvertising = percentageOfAdvertising;
    }

    /**
     * Gets periodicity per year.
     *
     * @return the periodicity per year
     */
    public Integer getPeriodicityPerYear() {
        return periodicityPerYear;
    }

    /**
     * Sets periodicity per year.
     *
     * @param periodicityPerYear the periodicity per year
     */
    public void setPeriodicityPerYear(Integer periodicityPerYear) {
        this.periodicityPerYear = periodicityPerYear;
    }

    /**
     * Gets number of pages.
     *
     * @return the number of pages
     */
    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Sets number of pages.
     *
     * @param numberOfPages the number of pages
     */
    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets sell id.
     *
     * @return the sell id
     */
    public Integer getSellId() {
        return sellId;
    }

    /**
     * Sets sell id.
     *
     * @param sellId the sell id
     */
    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }
}