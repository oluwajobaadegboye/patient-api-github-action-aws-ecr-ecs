package com.evicta.patientapi.exception;

public class PatientNotFoundException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE = "Patient with ID %d not found";

    public PatientNotFoundException(Long id) {
        super(String.format(MESSAGE_TEMPLATE, id));
    }

    public PatientNotFoundException(String message) {
        super(message);
    }
}
