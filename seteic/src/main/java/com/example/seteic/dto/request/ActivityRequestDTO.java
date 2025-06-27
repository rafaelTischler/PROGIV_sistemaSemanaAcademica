package com.example.seteic.dto.request;

import com.example.seteic.entity.ActivityType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityRequestDTO {

    private String title;
    private ActivityType type;
    private String content;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String location;

    private int capacity;

    private Long eventId;

    private List<Long> speakerIds; // IDs dos speakers para relacionamento ManyToMany
}
