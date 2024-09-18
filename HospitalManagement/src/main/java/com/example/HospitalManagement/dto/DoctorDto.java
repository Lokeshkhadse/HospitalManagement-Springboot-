package com.example.HospitalManagement.dto;

import lombok.Data;

@Data
public class DoctorDto {
    private Long doctor_id;
    private String name;
    private String specialization;
    private String contactNo;
    private String email;
}
