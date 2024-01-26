package com.nextevent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @Column(nullable = false)
    private int qty;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private TicketCategory ticketCategory;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Cart(int qty, TicketCategory ticketCategory, Customer customer) {
        this.qty = qty;
        this.ticketCategory = ticketCategory;
        this.customer = customer;
    }
}
