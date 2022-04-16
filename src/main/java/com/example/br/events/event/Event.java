package com.example.br.events.event;


import com.example.br.events.event.enums.EventType;
import com.example.br.events.venue.Venue;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table ( name = "event")
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false, unique = true, updatable = false)
    private Long event_id;

    @Column (nullable = false, unique = true)
    private String name;

    @Column (nullable = false)
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @Column
    @Enumerated
    private EventType type;

    @Column
    private Integer registration_count;

    @Column (nullable = false)
    private Long created_by;

    @Column
    private Long updated_by;

    @Column (nullable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column (nullable = false)
    @UpdateTimestamp
    private LocalDateTime updated_at;

    public Event fromEventRequestAndVenue(EventRequest eventRequest, Venue venue, Long user_id) {
        return Event.builder()
                .name(eventRequest.getName())
                .date(eventRequest.getDate())
                .venue(venue)
                .type(eventRequest.getType())
                .registration_count(eventRequest.getRegistration_count())
                .created_by(user_id)
                .updated_by(user_id)
                .build();
    }
}
