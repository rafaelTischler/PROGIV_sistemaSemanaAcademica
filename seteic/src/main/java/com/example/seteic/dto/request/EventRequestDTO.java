package com.example.seteic.dto.request;

import com.example.seteic.entity.Event;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestDTO {
    private String name;
    private int year;
    private String description;

    public Event toEntity() {
        return Event.builder()
                .name(this.name)
                .year(this.year)
                .description(this.description)
                .build();
    }
}
