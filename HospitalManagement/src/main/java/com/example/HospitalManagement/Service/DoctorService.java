package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Doctor;
import com.example.HospitalManagement.dto.DoctorDto;

import java.util.List;

public interface DoctorService {

//    Doctor createDoctor(Doctor doctor);
//
//    Doctor getDoctorById(Long doctor_id);
//
//    List<Doctor> getAllDoctors();
//
//    Doctor updateDoctor (Long doctor_id, Doctor doctor);
//
//    void deleteDoctor(Long doctor_id);
// Method to create a new doctor
DoctorDto createDoctor(DoctorDto doctorDto);

    // Method to retrieve a doctor by ID
    DoctorDto getDoctorById(Long doctor_id);

    // Method to retrieve all doctors
    List<DoctorDto> getAllDoctors();

    // Method to update an existing doctor
    DoctorDto updateDoctor(Long doctor_id, DoctorDto doctorDto);

    // Method to delete a doctor by ID
    void deleteDoctor(Long doctor_id);

}
