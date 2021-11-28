package com.periodical.trots.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

@Table(name = "periodical", indexes = {@Index(name = "idx_periodical_title", columnList = "title"), @Index(name = "fk_periodical_publisher1_idx", columnList = "publisher_id")}, uniqueConstraints = {@UniqueConstraint(name = "title_UNIQUE", columnNames = {"title"})})
@Entity
public class PeriodicalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sell_id", nullable = false)
    private Integer sellId;

    @Length(message = "Value should be from 4 to 45 symbols", min = 4, max = 45)
    @NotBlank(message = "Specify this field correct")
    @Column(name = "title", nullable = false, unique = true, length = 125)
    private String title;

    @NotNull(message = "Specify this field")
    @Positive(message = "Value should be positive")
    @Min(message = "Value should be more than 1", value = 1)
    @Column(name = "number_of_pages", nullable = false)
    private Integer numberOfPages;

    @NotNull(message = "Specify this field")
    @Positive(message = "Value should be positive")
    @Min(message = "Value should be more than 1", value = 1)
    @Column(name = "periodicity_per_year", nullable = false)
    private Integer periodicityPerYear;

    @NotNull(message = "Specify this field")
    @Positive(message = "Value should be positive")
    @Min(message = "Value should be more than 0", value = 0)
    @Column(name = "percentage_of_advertising", nullable = false)
    private Integer percentageOfAdvertising;

    @NotNull(message = "Specify this field")
    @Positive(message = "Value should be positive")
    @Min(message = "Value should be more than 1", value = 1)
    @Column(name = "price_per_month", nullable = false)
    private BigDecimal pricePerMonth;

    @Length(message = "Description should be from 5 to 1000 symbols", min = 5, max = 1000)
    @NotBlank(message = "Specify this field")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Specify this field")
    @Max(message = "Value should be less than 5", value = 5)
    @Min(message = "Value should be more than 0", value = 0)
    @Column(name = "rating")
    private Double rating;

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    private PublisherEntity publisher;

    @NotNull(message = "Select any image")
    @Column(name = "images", nullable = false, length = 500)
    private String images;

    @OneToMany(mappedBy = "periodical")
    Set<PeriodicalHasReceiptEntity> receiptEntities;

    @OneToMany(mappedBy = "periodical")
    Set<PeriodicalHasSubjectEntity> periodicalHasSubject;

    public Set<PeriodicalHasReceiptEntity> getReceiptEntities() {
        return receiptEntities;
    }

    public void setReceiptEntities(Set<PeriodicalHasReceiptEntity> receiptEntities) {
        this.receiptEntities = receiptEntities;
    }

    public Set<PeriodicalHasSubjectEntity> getPeriodicalHasSubject() {
        return periodicalHasSubject;
    }

    public void setPeriodicalHasSubject(Set<PeriodicalHasSubjectEntity> periodicalHasSubject) {
        this.periodicalHasSubject = periodicalHasSubject;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(BigDecimal pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public Integer getPercentageOfAdvertising() {
        return percentageOfAdvertising;
    }

    public void setPercentageOfAdvertising(Integer percentageOfAdvertising) {
        this.percentageOfAdvertising = percentageOfAdvertising;
    }

    public Integer getPeriodicityPerYear() {
        return periodicityPerYear;
    }

    public void setPeriodicityPerYear(Integer periodicityPerYear) {
        this.periodicityPerYear = periodicityPerYear;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }
}