package com.example.seteic.controller;

import com.example.seteic.dto.request.ActivityRequestDTO;
import com.example.seteic.dto.response.ActivityResponseDTO;
import com.example.seteic.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<ActivityResponseDTO> getAll() {
        return activityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> getById(@PathVariable Long id) {
        return activityService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ActivityResponseDTO> create(@RequestBody ActivityRequestDTO dto) {
        return activityService.save(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> update(@PathVariable Long id, @RequestBody ActivityRequestDTO dto) {
        return activityService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
