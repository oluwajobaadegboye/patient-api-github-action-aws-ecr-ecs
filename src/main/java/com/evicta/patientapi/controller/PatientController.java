package com.evicta.patientapi.controller;


import com.evicta.patientapi.dto.PatientRequest;
import com.evicta.patientapi.dto.PatientResponse;
import com.evicta.patientapi.service.PatientService;
import com.evicta.patientapi.validation.Create;
import com.evicta.patientapi.validation.Update;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {
    private final PatientService service;

//    public PatientController(PatientService service) {
//        this.service = service;
//    }

    @GetMapping
    public Page<PatientResponse> list(
            @RequestParam(required = false) String name,
            @PageableDefault(sort = "lastName", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.search(name, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> get(@PathVariable Long id) {
        PatientResponse resp = service.getById(id);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<PatientResponse> create(@RequestBody @Valid @Validated(Create.class) PatientRequest req) {
        PatientResponse created = service.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatientResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid  @Validated(Update.class) PatientRequest req) {
        PatientResponse updated = service.update(id, req);
        return ResponseEntity.ok(updated);
    }
}