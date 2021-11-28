package com.periodical.trots.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @Length(message = "Value shold be from 11 to 12 symblols, incude Country-code to this value", min = 11, max = 12)
    @Column(name = "telephone_number", nullable = false, length = 14)
    private String telephoneNumber;

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}