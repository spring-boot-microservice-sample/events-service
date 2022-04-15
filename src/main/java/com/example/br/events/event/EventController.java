package com.example.br.events.event;

import com.example.br.events.response.ResponseHandler;
import com.example.br.events.venue.Venue;
import com.example.br.events.venue.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VenueRepository venueRepository;

    @GetMapping
    public ResponseEntity<Object> getAll() {

        try {
            List<Event> data = eventRepository.findAll();
            return ResponseHandler.response(
                    "Events Retrieved Successfully",
                    HttpStatus.OK,
                    data
            );
        } catch (Exception e) {
            return ResponseHandler.response( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody EventRequest eventRequest) {

        if(eventRepository.existsByName(eventRequest.getName())){
            return ResponseHandler.response(
                    eventRequest.getName() + " Event already Exists",
                    HttpStatus.BAD_REQUEST
            );
        }

        Optional<Venue> venue = venueRepository.findById(eventRequest.getVenue_id());
        if(!venue.isPresent()){
            return ResponseHandler.response(
                    "Venue does not exists",
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            Event event = new Event().fromEventRequestAndVenue(eventRequest, venue.get());
            eventRepository.save(event);
            return ResponseHandler.response(
                    event.getName() + " Event added successfully",
                    HttpStatus.CREATED
            );
        } catch (IllegalArgumentException e) {
            return ResponseHandler.response( e.getMessage(), HttpStatus.BAD_REQUEST );
        } catch (Exception e) {
            return ResponseHandler.response( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
}
