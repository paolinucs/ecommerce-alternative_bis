package it.paolone.ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin-id")
    private Long id;
    @Column(name = "nominative")
    private String nominative;
    @Column(name = "phone-number")
    private String phoneNumber;
    @Column(name = "email", unique = true)
    private User user;
    @Column(name = "role")
    private String roles;


}
