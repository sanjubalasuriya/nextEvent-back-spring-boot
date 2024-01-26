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
@Table(name = "ticket_category")
public class TicketCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_category_id")
    private int ticketCategoryId;

    @Column(name = "category")//Silver | Gold
    private String category;

    @Column(name = "ticket_price")
    private int ticketPrice;

    @Column(name = "ticket_count")
    private int ticketCount;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "ticket_category")
    private Set<Cart> cart;

    public TicketCategory(String category, int ticketPrice, int ticketCount, Event event) {
        this.category = category;
        this.ticketPrice = ticketPrice;
        this.ticketCount = ticketCount;
        this.event = event;
    }
}
