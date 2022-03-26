package com.example.br.events.event;


import com.example.br.events.event.enums.EventType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table ( name = "event")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false, unique = true, updatable = false)
    private Long id;

    @Column
    private String name;

    @Column (nullable = false)
    private LocalDate date;

    @Column
    private Long venue_id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Event event = (Event) o;
        return id != null && Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
