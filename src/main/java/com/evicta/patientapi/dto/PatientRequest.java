package com.evicta.patientapi.dto;

import com.evicta.patientapi.validation.Create;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PatientRequest(
        @NotBlank(groups = {Create.class}, message = "FirstName must not be blank or empty") String firstName,
        @NotBlank(groups = {Create.class}, message = "lastName must not be blank or empty")  String lastName,
        @Email(message = "email must be in correct format") @NotBlank(groups = {Create.class}, message = "email must not be blank or empty")  String email,
        @Pattern(regexp="\\d{3}-\\d{2}-\\d{4}", message = "invalid ssn format, follow xxx-xx-xxxx") @NotBlank(groups = {Create.class},
                message = "ssn must not be blank or empty") String ssn
) {}