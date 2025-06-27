package com.example.seteic.dto.response;

import com.example.seteic.entity.Activity;
import com.example.seteic.entity.ActivityType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityResponseDTO {

    private Long id;
    private String title;
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

    private Long eventId;
    private List<Long> speakerIds;

    public static ActivityResponseDTO fromEntity(Activity activity) {
        return ActivityResponseDTO.builder()
                .id(activity.getId())
                .title(activity.getTitle())
                .type(activity.getType())
                .content(activity.getContent())
                .startDateTime(activity.getStartDateTime())
                .endDateTime(activity.getEndDateTime())
                .location(activity.getLocation())
                .qrToken(activity.getQrToken())
                .qrExpiration(activity.getQrExpiration())
                .capacity(activity.getCapacity())
                .createdAt(activity.getCreatedAt())
                .updatedAt(activity.getUpdatedAt())
                .deleted(activity.isDeleted())
                .deletedAt(activity.getDeletedAt())
                .eventId(activity.getEvent() != null ? activity.getEvent().getId() : null)
                .speakerIds(activity.getSpeakers() != null ?
                        activity.getSpeakers().stream().map(s -> s.getId()).collect(Collectors.toList()) : null)
                .build();
    }
}
