package com.example.seteic.entity;

import com.example.seteic.dto.request.EventRequestDTO;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int year;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private boolean deleted;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activity> activities;

    public EventRequestDTO toDTO() {
        return EventRequestDTO.builder()
                .name(this.name)
                .year(this.year)
                .description(this.description)
                .build();
    }
}
