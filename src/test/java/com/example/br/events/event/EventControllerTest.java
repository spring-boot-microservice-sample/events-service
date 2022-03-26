package com.example.br.events.event;

import com.example.br.events.event.enums.EventType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
@MockBean(JpaMetamodelMappingContext.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private EventRepository eventRepository;

    Event EVENT_1 = Event.builder()
            .name("Wrestlemania")
            .id(1L)
            .date(LocalDate.from(LocalDateTime.of(2022, 4,2, 5, 0)))
            .venue_id(1L)
            .type(EventType.OFFLINE)
            .registration_count(100000)
            .created_by(1L)
            .updated_by(1L)
            .build();

    Event EVENT_2 = Event.builder()
            .name("TATA IPL")
            .id(2L)
            .date(LocalDate.from(LocalDateTime.of(2022, 3,26, 7, 0)))
            .venue_id(2L)
            .type(EventType.OFFLINE)
            .registration_count(50000)
            .created_by(1L)
            .updated_by(1L)
            .build();

    @Test
    public void getAll_success() throws Exception {
        List<Event> events = new ArrayList<>(Arrays.asList(EVENT_1, EVENT_2));

        Mockito.when(eventRepository.findAll()).thenReturn(events);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/events")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[1].name", is("TATA IPL")));
    }

    @Test
    public void add_success() throws Exception {

        Mockito.when(eventRepository.save(EVENT_1)).thenReturn(EVENT_1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(EVENT_1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(EVENT_1.getName() + " Event added successfully") ));
    }

    @Test
    public void add_failure_null_request() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(null));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void add_failure_bad_request() throws Exception {

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(null));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }
}