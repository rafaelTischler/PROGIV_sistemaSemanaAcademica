package com.example.seteic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import com.example.seteic.dto.request.ActivityRequestDTO;
import com.example.seteic.dto.response.ActivityResponseDTO;

@Entity
@Table(name = "activity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    private String content;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private String location;

    private String qrToken;
    private LocalDateTime qrExpiration;

    private int capacity;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted;
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToMany
    @JoinTable(name = "activity_speaker", joinColumns = @JoinColumn(name = "activity_id"), inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<Speaker> speakers;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations;

    public static Activity toEntity(ActivityRequestDTO dto) {
        Activity activity = Activity.builder()
                .title(dto.getTitle())
                .type(dto.getType())
                .content(dto.getContent())
                .startDateTime(dto.getStartDateTime())
                .endDateTime(dto.getEndDateTime())
                .location(dto.getLocation())
                .capacity(dto.getCapacity())
                .deleted(false)
                .build();

        return activity;
    }

    public ActivityResponseDTO toDTO() {
        return ActivityResponseDTO.fromEntity(this);
    }

}
