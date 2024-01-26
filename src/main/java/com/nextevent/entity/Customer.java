package com.nextevent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "gender")
    private String gender;

    @Column(name = "nic_or_passport_prefix")
    private String nicOrPassportPrefix;

    @Column(name = "nic_or_passport")
    private String nicOrPassport;

    @Column(name = "mobile")
    private String mobile;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "customer")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "customer")
    private Set<Cart> cart;


    public Customer(String gender, String nicOrPassportPrefix, String nicOrPassport, String mobile, User user) {
        this.gender = gender;
        this.nicOrPassportPrefix = nicOrPassportPrefix;
        this.nicOrPassport = nicOrPassport;
        this.mobile = mobile;
        this.user = user;
    }
}
