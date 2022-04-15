package com.example.br.events.venue;

import com.example.br.events.event.Event;
import com.example.br.events.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/venue")
public class VenueController {

    @Autowired
    VenueRepository venueRepository;

    @GetMapping
    public ResponseEntity<Object> getAll() {

        try {
            List<Venue> data = venueRepository.findAll();
            return ResponseHandler.response(
                    "Venues Retrieved Successfully",
                    HttpStatus.OK,
                    data
            );
        } catch (Exception e) {
            return ResponseHandler.response( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Venue venue) {

        try {
            venueRepository.save(venue);
            return ResponseHandler.response(
                    venue.getName() + " Event added successfully",
                    HttpStatus.CREATED
            );
        } catch (IllegalArgumentException e) {
            return ResponseHandler.response( e.getMessage(), HttpStatus.BAD_REQUEST );
        } catch (Exception e) {
            return ResponseHandler.response( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
}
