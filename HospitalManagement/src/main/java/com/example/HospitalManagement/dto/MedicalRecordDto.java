package com.example.HospitalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class MedicalRecordDto {
    private Long medicalrecord_id;
    private Date recordDate;
    private String details;
    private Long patientId;
    private Long doctorId;

    // Constructor
    public MedicalRecordDto(Long medicalrecord_id, Date recordDate, String details, Long patientId, Long doctorId) {
        this.medicalrecord_id = medicalrecord_id;
        this.recordDate = recordDate;
        this.details = details;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    // Getters and setters
}


