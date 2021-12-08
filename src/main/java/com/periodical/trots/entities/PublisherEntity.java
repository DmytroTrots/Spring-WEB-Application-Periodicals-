package com.periodical.trots.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * The type Publisher entity.
 */
@Table(name = "publisher", indexes = {@Index(name = "name_UNIQUE", columnList = "name", unique = true)})
@Entity
public class PublisherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(message = "Specify this field correctly")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "telephone_number", length = 14)
    private String telephoneNumber;

    /**
     * Instantiates a new Publisher entity.
     *
     * @param id the id
     */
    public PublisherEntity(Integer id) {
        this.id = id;
    }

    /**
     * Instantiates a new Publisher entity.
     */
    public PublisherEntity() {
    }

    /**
     * Gets telephone number.
     *
     * @return the telephone number
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets telephone number.
     *
     * @param telephoneNumber the telephone number
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}