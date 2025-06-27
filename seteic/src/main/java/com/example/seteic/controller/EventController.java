package com.example.seteic.controller;

import com.example.seteic.dto.request.EventRequestDTO;
import com.example.seteic.entity.Event;
import com.example.seteic.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventRequestDTO> getAllEvents() {
        return eventService.findAll().stream()
                .map(Event::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventRequestDTO> getEventById(@PathVariable Long id) {
        return eventService.findById(id)
                .map(event -> ResponseEntity.ok(event.toDTO()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EventRequestDTO> createEvent(@RequestBody EventRequestDTO dto) {
        Event saved = eventService.save(dto.toEntity());
        return ResponseEntity.ok(saved.toDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventRequestDTO> updateEvent(@PathVariable Long id, @RequestBody EventRequestDTO dto) {
        return eventService.updateEvent(id, dto)
                .map(updated -> ResponseEntity.ok(updated.toDTO()))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
