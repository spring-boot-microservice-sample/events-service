package com.example.br.events.event;

import com.example.br.events.event.enums.EventType;
import com.example.br.events.venue.Venue;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

class EventControllerTest {

    Venue VENUE_1 = Venue.builder()
            .name("AT&T Stadium")
            .venue_id(1L)
            .max_capacity(10000)
            .address("1 AT&T Way, Arlington, TX 76011, United States")
            .build();

    Event EVENT_1 = Event.builder()
            .name("Wrestlemania")
            .event_id(1L)
            .date(LocalDate.from(LocalDateTime.of(2022, 4,2, 5, 0)))
            .venue(VENUE_1)
            .type(EventType.OFFLINE)
            .registration_count(100000)
            .created_by(1L)
            .updated_by(1L)
            .build();

    Event EVENT_2 = Event.builder()
            .name("TATA IPL")
            .event_id(2L)
            .date(LocalDate.from(LocalDateTime.of(2022, 3,26, 7, 0)))
            .venue(VENUE_1)
            .type(EventType.OFFLINE)
            .registration_count(50000)
            .created_by(1L)
            .updated_by(1L)
            .build();

    private String ENDPOINT = "/api/event";

}