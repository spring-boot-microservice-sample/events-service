package com.example.br.events.event;

import com.example.br.events.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events")
    public ResponseEntity<Object> getAll() {

        try {
            List<Event> data = eventRepository.findAll();
            return ResponseHandler.response(
                    "Data Retrieved Successfully",
                    HttpStatus.OK,
                    data
            );
        } catch (Exception e) {
            return ResponseHandler.response( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
        }

    }

    @PostMapping("/events")
    public ResponseEntity<Object> add(@RequestBody Event event) {

        try {
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
