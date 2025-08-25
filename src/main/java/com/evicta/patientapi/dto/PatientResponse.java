package com.evicta.patientapi.dto;

import com.evicta.patientapi.entity.Patient;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PatientResponse{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String maskedSsn;

    public static PatientResponse from(Patient patient) {
        String last4 = patient.getSsn().substring(patient.getSsn().length() - 4);
        return new PatientResponse(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getEmail(),
                "***-**-" + last4
        );
    }


}
