package com.periodical.trots.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * The type User entity.
 */
@Entity
@Table(name = "user", indexes = {@Index(name = "email_UNIQUE", columnList = "email", unique = true), @Index(name = "telephone_UNIQUE", columnList = "telephone", unique = true), @Index(name = "username_UNIQUE", columnList = "username", unique = true)})
public class UserEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true, length = 16)
    @NotBlank(message = "{error.username}")
    @Pattern(regexp = "[a-zA-Z0-9]{6,18}", message = "{error.username}")
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{error.email}")
    private String email;

    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "telephone", nullable = false, unique = true, length = 14)
    @Pattern(regexp = "[0-9]{11,12}", message = "{error.telephone}")
    private String telephone;

    @Column(name = "name", nullable = false, length = 45)
    @Pattern(regexp = "[а-яА-ЯёЁa-zA-Z]{1,25}", message = "{error.name}")
    private String name;

    @Column(name = "surname", nullable = false, length = 45)
    @Pattern(regexp = "[а-яА-ЯёЁa-zA-Z]{1,25}", message = "{error.surname}")
    private String surname;

    @Column(name = "ban_status", length = 45)
    private String banStatus;

    @Column(name = "balance", columnDefinition="Decimal(9,2) default '0.00'")
    private BigDecimal balance;

    @Column(name = "role", length = 45)
    private String role;


    @NotBlank(message = "Specify this value correctly")
    @Column(name = "address", length = 1024)
    @Length(max = 1024, message = "{error.address}")
    private String address;

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets ban status.
     *
     * @return the ban status
     */
    public String getBanStatus() {
        return banStatus;
    }

    /**
     * Sets ban status.
     *
     * @param banStatus the ban status
     */
    public void setBanStatus(String banStatus) {
        this.banStatus = banStatus;
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
     * Gets telephone.
     *
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets telephone.
     *
     * @param telephone the telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
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