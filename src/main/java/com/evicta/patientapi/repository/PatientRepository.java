package com.evicta.patientapi.repository;


import com.evicta.patientapi.entity.Patient;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("""
    SELECT p FROM Patient p
    WHERE (:name IS NULL OR LOWER(p.firstName) LIKE LOWER(CONCAT('%', :name, '%'))
       OR  LOWER(p.lastName)  LIKE LOWER(CONCAT('%', :name, '%')))
  """)
    Page<Patient> searchByName(@Param("name") String name, Pageable pageable);

    boolean existsByEmail(String email);
}

