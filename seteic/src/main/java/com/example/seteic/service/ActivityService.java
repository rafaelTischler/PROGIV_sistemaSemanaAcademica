package com.example.seteic.service;

import com.example.seteic.dto.request.ActivityRequestDTO;
import com.example.seteic.dto.response.ActivityResponseDTO;
import com.example.seteic.entity.Activity;
import com.example.seteic.entity.Event;
import com.example.seteic.entity.Speaker;
import com.example.seteic.repository.ActivityRepository;
import com.example.seteic.repository.EventRepository;
import com.example.seteic.repository.SpeakerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final EventRepository eventRepository;
    private final SpeakerRepository speakerRepository;

    public ActivityService(ActivityRepository activityRepository,
                           EventRepository eventRepository,
                           SpeakerRepository speakerRepository) {
        this.activityRepository = activityRepository;
        this.eventRepository = eventRepository;
        this.speakerRepository = speakerRepository;
    }

    public List<ActivityResponseDTO> findAll() {
        return activityRepository.findAll().stream()
                .map(ActivityResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<ActivityResponseDTO> findById(Long id) {
        return activityRepository.findById(id)
                .map(ActivityResponseDTO::fromEntity);
    }

    public Optional<ActivityResponseDTO> save(ActivityRequestDTO dto) {
        Optional<Event> eventOpt = eventRepository.findById(dto.getEventId());
        if (eventOpt.isEmpty()) {
            return Optional.empty();
        }

        Activity activity = Activity.toEntity(dto);
        activity.setEvent(eventOpt.get());
        activity.setCreatedAt(LocalDateTime.now());
        activity.setUpdatedAt(LocalDateTime.now());

        // Buscar e setar speakers
        if (dto.getSpeakerIds() != null && !dto.getSpeakerIds().isEmpty()) {
            List<Speaker> speakers = speakerRepository.findAllById(dto.getSpeakerIds());
            activity.setSpeakers(speakers);
        }

        Activity saved = activityRepository.save(activity);
        return Optional.of(ActivityResponseDTO.fromEntity(saved));
    }

    public Optional<ActivityResponseDTO> update(Long id, ActivityRequestDTO dto) {
        return activityRepository.findById(id).flatMap(existing -> {
            existing.setTitle(dto.getTitle());
            existing.setType(dto.getType());
            existing.setContent(dto.getContent());
            existing.setStartDateTime(dto.getStartDateTime());
            existing.setEndDateTime(dto.getEndDateTime());
            existing.setLocation(dto.getLocation());
            existing.setCapacity(dto.getCapacity());

            eventRepository.findById(dto.getEventId()).ifPresent(existing::setEvent);

            if (dto.getSpeakerIds() != null) {
                List<Speaker> speakers = speakerRepository.findAllById(dto.getSpeakerIds());
                existing.setSpeakers(speakers);
            }

            existing.setUpdatedAt(LocalDateTime.now());

            return Optional.of(ActivityResponseDTO.fromEntity(activityRepository.save(existing)));
        });
    }

    public void deleteById(Long id) {
        activityRepository.findById(id).ifPresent(activity -> {
            activity.setDeleted(true);
            activity.setDeletedAt(LocalDateTime.now());
            activityRepository.save(activity);
        });
    }
}
