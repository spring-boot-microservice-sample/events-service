package com.example.br.events.venue;

import com.example.br.events.event.Event;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table ( name = "venue")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Venue {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false, unique = true, updatable = false)
    private Long venue_id;

    @Column (nullable = false, unique = true)
    private String name;

    @Column (nullable = false)
    private Integer max_capacity;

    @Column
    private String address;

    @Column (nullable = false)
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column (nullable = false)
    @UpdateTimestamp
    private LocalDateTime updated_at;
}
