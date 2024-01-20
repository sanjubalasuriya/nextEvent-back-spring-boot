package com.nextevent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private int ticketId;

    @Column(name = "ticketNumber")
    private String ticketNumber;

    @Column(name = "ticketPrice")
    private int ticketPrice;

//    @Column(name = "seatNumber")
//    private String seatNumber;

    @Column(name = "ticketValidDate")
    private Date ticketValidDate;

    @Column(name = "is_valid")
    private boolean isValid;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    public Ticket(String ticketNumber, int ticketPrice, Date ticketValidDate, boolean isValid, Customer customer) {
        this.ticketNumber = ticketNumber;
        this.ticketPrice = ticketPrice;
        this.ticketValidDate = ticketValidDate;
        this.isValid = isValid;
        this.customer = customer;
    }
}
