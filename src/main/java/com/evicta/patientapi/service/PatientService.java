package com.evicta.patientapi.service;

import com.evicta.patientapi.dto.PatientRequest;
import com.evicta.patientapi.dto.PatientResponse;
import com.evicta.patientapi.entity.Patient;
import com.evicta.patientapi.exception.DuplicateResourceException;
import com.evicta.patientapi.exception.PatientNotFoundException;
import com.evicta.patientapi.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repo;

    public Page<PatientResponse> search(String name, Pageable pageable) {
        return repo.searchByName(name, pageable).map(PatientResponse::from);
    }

    public PatientResponse create(PatientRequest req) {
       if(repo.existsByEmail(req.email())){
            throw new DuplicateResourceException("Email already exists: " + req.email());
       }

        Patient p = Patient.builder()
                .firstName(req.firstName())
                .lastName(req.lastName())
                .email(req.email())
                .ssn(req.ssn())
                .build();
        return PatientResponse.from(repo.save(p));
    }

    public PatientResponse getById(Long id) {
        return repo.findById(id)
                .map(PatientResponse::from)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }

    public PatientResponse update(Long id, PatientRequest req) {
        Patient savedPatient = repo.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
        savedPatient.setFirstName(getValue(req.firstName(), savedPatient.getFirstName()));
        savedPatient.setLastName(getValue(req.lastName(), savedPatient.getLastName()));
        savedPatient.setEmail(getValue(req.email(), savedPatient.getEmail()));
        savedPatient.setSsn(getValue(req.ssn(), savedPatient.getSsn()));
        savedPatient = repo.save(savedPatient);
        return PatientResponse.from(savedPatient);
    }

    private String getValue(String newValue, String oldValue) {
        return newValue != null && !newValue.isBlank() ? newValue : oldValue;
    }
}
