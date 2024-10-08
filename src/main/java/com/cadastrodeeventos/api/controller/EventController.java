package com.cadastrodeeventos.api.controller;

import com.cadastrodeeventos.api.domain.event.Event;
import com.cadastrodeeventos.api.domain.event.EventRequestDTO;
import com.cadastrodeeventos.api.domain.event.EventResponseDTO;
import com.cadastrodeeventos.api.service.EventService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private EventService service;

    public EventController(EventService service) {
        this.service = service;
    }
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> create(@RequestParam("title") String title,
                                        @RequestParam(value = "description", required = false) String description,
                                        @RequestParam("date") Long date,
                                        @RequestParam("city") String city,
                                        @RequestParam("state") String state,
                                        @RequestParam("remote") Boolean remote,
                                        @RequestParam("eventUrl") String eventUrl,
                                        @RequestParam(value = "image", required = false) MultipartFile image) {
        EventRequestDTO eventRequestDTO = new EventRequestDTO(title, description, date, city, state, remote, eventUrl, image);
        Event newEvent = this.service.createEvent(eventRequestDTO);
        return ResponseEntity.ok(newEvent);
    }

    public ResponseEntity<List<EventResponseDTO>> getEvents(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(this.service.getAllEvents(page, size));
    }
}
