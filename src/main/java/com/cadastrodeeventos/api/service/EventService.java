package com.cadastrodeeventos.api.service;

import com.cadastrodeeventos.api.domain.event.Event;
import com.cadastrodeeventos.api.domain.event.EventRequestDTO;
import com.cadastrodeeventos.api.repositories.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event criarEvento(EventRequestDTO event){
        Event newEvent = new Event();
        String imgUrl = event.image() == null ? "" : this.uploadImage(event.image());
        newEvent.setDate(event.date());
        newEvent.setDescription(event.description());
        newEvent.setEventUrl(event.eventUrl());
        newEvent.setTitle(event.title());
        newEvent.setImgUrl(imgUrl);
    return eventRepository.save(newEvent);
    }

    private String uploadImage(MultipartFile file){
        return "";
    }
}
