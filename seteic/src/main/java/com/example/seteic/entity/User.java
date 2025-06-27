package com.example.seteic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(unique = true)
    private String cpf;

    @Enumerated(EnumType.STRING)
    private UserType type;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations;
}
