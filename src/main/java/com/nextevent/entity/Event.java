package com.nextevent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_location")
    private String eventLocation;

    @Column(name = "event_date")
    private Date eventDate;

    @Column(name = "time")
    private Date time;

    @Column(name = "event_banner")
    private String eventBanner;

    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private EventOrganizer eventOrganizer;

    @OneToMany(mappedBy = "event")
    private Set<TicketCategory> ticketCategory;

    public Event(String eventName, String eventLocation, Date eventDate, Date time, String eventBanner, EventOrganizer eventOrganizer) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.time = time;
        this.eventBanner = eventBanner;
        this.eventOrganizer = eventOrganizer;
    }
}
