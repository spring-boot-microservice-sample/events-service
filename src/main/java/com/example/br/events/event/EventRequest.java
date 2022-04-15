package com.example.br.events.event;

import com.example.br.events.event.enums.EventType;
import com.example.br.events.venue.Venue;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private String name;

    private LocalDate date;

    private Long venue_id;

    private EventType type;

    private Integer registration_count;

    private Long created_by;

    private Long updated_by;
}
