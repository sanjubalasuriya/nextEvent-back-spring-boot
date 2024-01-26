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
@Table(name = "event_organizer")
public class EventOrganizer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizer_d")
    private int organizerId;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "eventOrganizer")
    private Set<Event> events;



    public EventOrganizer(String mobile, String address, User user) {
        this.mobile = mobile;
        this.address = address;
        this.user = user;
    }

    public EventOrganizer(int organizerId, String mobile, String address) {
        this.organizerId = organizerId;
        this.mobile = mobile;
        this.address = address;
    }
}
