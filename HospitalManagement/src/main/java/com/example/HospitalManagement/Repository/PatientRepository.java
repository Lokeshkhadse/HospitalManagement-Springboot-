package com.example.HospitalManagement.Repository;

import com.example.HospitalManagement.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByName(String username);
}
