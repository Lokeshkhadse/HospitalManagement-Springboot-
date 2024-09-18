package com.example.HospitalManagement.controller;


import com.example.HospitalManagement.CommonResponse.Response;
import com.example.HospitalManagement.Entity.Doctor;

import com.example.HospitalManagement.Service.DoctorService;
import com.example.HospitalManagement.dto.DoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Response<DoctorDto>> createDoctor(@RequestBody DoctorDto doctorDto) {
    DoctorDto createdDoctor = doctorService.createDoctor(doctorDto);
    Response<DoctorDto> response = Response.successfulResponse("Doctor created successfully", createdDoctor);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
}


    @GetMapping("/{doctor_id}")
    public ResponseEntity<Response<DoctorDto>> getDoctorById(@PathVariable("doctor_id") Long doctor_id) {
        DoctorDto doctorDto = doctorService.getDoctorById(doctor_id);
        Response<DoctorDto> response = Response.successfulResponse("Doctor fetched successfully", doctorDto);
        return ResponseEntity.ok(response);
    }

@GetMapping
public ResponseEntity<Response<List<DoctorDto>>> getAllDoctors() {
    List<DoctorDto> doctors = doctorService.getAllDoctors();
    Response<List<DoctorDto>> response = Response.successfulResponse("All doctors fetched successfully", doctors);
    return ResponseEntity.ok(response);
}

    @PutMapping("/{doctor_id}")
    public ResponseEntity<Response<DoctorDto>> updateDoctor(@PathVariable("doctor_id") Long doctor_id, @RequestBody DoctorDto doctorDto) {
        DoctorDto updatedDoctor = doctorService.updateDoctor(doctor_id, doctorDto);
        Response<DoctorDto> response = Response.successfulResponse("Doctor updated successfully", updatedDoctor);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{doctor_id}")
    public ResponseEntity<Response<Void>> deleteDoctor(@PathVariable("doctor_id") Long doctor_id) {
        doctorService.deleteDoctor(doctor_id);
        Response<Void> response = Response.successfulResponse("Doctor deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
