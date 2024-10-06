package com.cadastrodeeventos.api.controller;

import com.cadastrodeeventos.api.domain.event.Event;
import com.cadastrodeeventos.api.domain.event.EventRequestDTO;
import com.cadastrodeeventos.api.service.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    private EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    public Event criarEvento(EventRequestDTO event){
        return service.criarEvento(event);
    }

}
