package com.example.seteic.service;

import com.example.seteic.dto.request.EventRequestDTO;
import com.example.seteic.entity.Event;
import com.example.seteic.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public Event save(Event event) {
        if (event.getCreatedAt() == null) {
            event.setCreatedAt(LocalDateTime.now());
        }
        event.setUpdatedAt(LocalDateTime.now());
        return eventRepository.save(event);
    }

    public Optional<Event> updateEvent(Long id, EventRequestDTO dto) {
        return eventRepository.findById(id).map(event -> {
            event.setName(dto.getName());
            event.setYear(dto.getYear());
            event.setDescription(dto.getDescription());
            event.setUpdatedAt(LocalDateTime.now());
            return eventRepository.save(event);
        });
    }

    public void deleteById(Long id) {
        eventRepository.findById(id).ifPresent(event -> {
            event.setDeleted(true);
            event.setDeletedAt(LocalDateTime.now());
            eventRepository.save(event);
        });
    }
}
