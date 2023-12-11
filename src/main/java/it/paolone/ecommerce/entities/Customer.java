package it.paolone.ecommerce.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long customerId;
    @Column(name = "nominative")
    private String nominative;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

    // getters and setters

    public Long getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public String getNominative() {
        return nominative;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNominative(String nominative) {
        this.nominative = nominative;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
