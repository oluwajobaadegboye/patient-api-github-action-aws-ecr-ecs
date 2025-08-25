package com.evicta.patientapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) @NotBlank
    private String firstName;

    @Column(nullable = false) @NotBlank
    private String lastName;

    @Column(unique = true, nullable = false) @Email
    private String email;

    // PHI – store but never expose directly
    @Column(length = 11, nullable = false) // e.g., 123-45-6789
    @Pattern(regexp = "\\d{3}-\\d{2}-\\d{4}")
    private String ssn;

    @Version
    private Long version;
}
