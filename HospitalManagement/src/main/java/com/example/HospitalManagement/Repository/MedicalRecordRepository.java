package com.example.HospitalManagement.Repository;

import com.example.HospitalManagement.Entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord ,Long> {
//    List<MedicalRecord> findByPatientId(Long medicalreocrd_id);


}
