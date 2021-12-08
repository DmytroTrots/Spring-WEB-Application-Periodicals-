package com.periodical.trots.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * The type Receipt entity.
 */
@Table(name = "receipt", indexes = {@Index(name = "fk_receipt_user1_idx", columnList = "user_id"), @Index(name = "fk_receipt_status1_idx", columnList = "status_id")})
@Entity
public class ReceiptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @Column(name = "adress", nullable = false, length = 1024)
    private String adress;

    @Column(name = "telephone_number", nullable = false, length = 14)
    private String telephoneNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusEntity status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    /**
     * The Receipt entities.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mReceipt")
    Set<PeriodicalHasReceiptEntity> receiptEntities;

    /**
     * Instantiates a new Receipt entity.
     *
     * @param id the id
     */
    public ReceiptEntity(Integer id) {
        this.id = id;
    }

    /**
     * Instantiates a new Receipt entity.
     */
    public ReceiptEntity() {
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
     * Gets create time.
     *
     * @return the create time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime the create time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public StatusEntity getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(StatusEntity status) {
        this.status = status;
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
     * Gets adress.
     *
     * @return the adress
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Sets adress.
     *
     * @param adress the adress
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
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

    @Override
    public String toString() {
        return "" + receiptEntities + "";
    }
}