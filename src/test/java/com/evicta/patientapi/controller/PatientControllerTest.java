package com.evicta.patientapi.controller;

import com.evicta.patientapi.dto.PatientResponse;
import com.evicta.patientapi.exception.PatientNotFoundException;
import com.evicta.patientapi.service.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = PatientController.class)
//@AutoConfigureMockMvc
class PatientControllerTest {
    @MockitoBean
    private PatientService service;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void get_patient_by_id_ok() throws Exception {
        var resp = new PatientResponse(1L, "Ada", "Lovelace", "ada@ex.com", "***-**-6789");
        when(service.getById(1L)).thenReturn(resp);

        mockMvc.perform(get("/api/v1/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Ada"))
                .andExpect(jsonPath("$.maskedSsn").value("***-**-6789"));
    }

    @Test
    void get_patient_not_found() throws Exception {
        when(service.getById(42L)).thenThrow(new PatientNotFoundException(42L));
        mockMvc.perform(get("/api/v1/patients/42")).andExpect(status().isNotFound());
    }
}