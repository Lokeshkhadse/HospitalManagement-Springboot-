package com.example.HospitalManagement.controller;



import com.example.HospitalManagement.CommonResponse.Response;
import com.example.HospitalManagement.Entity.Patient;

import com.example.HospitalManagement.Service.PatientService;
import com.example.HospitalManagement.dto.PatientDto;
import com.example.HospitalManagement.mapper.PatientMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @Autowired
    private PatientMapper patientMapper;


@PostMapping("/register")
public ResponseEntity<Response<PatientDto>> createPatient(@RequestBody PatientDto patientDto) {
    Patient createdPatient = patientService.createPatient(patientMapper.toEntity(patientDto));
    PatientDto responseDto = patientMapper.toDto(createdPatient);
    Response<PatientDto> response = Response.successfulResponse("Patient created successfully", responseDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
}

@GetMapping("/{patient_id}")
public ResponseEntity<Response<PatientDto>> getPatientById(@PathVariable("patient_id") Long patient_id) {
    Patient patient = patientService.getPatientById(patient_id);
    PatientDto responseDto = patientMapper.toDto(patient);
    Response<PatientDto> response = Response.successfulResponse("Patient fetched successfully", responseDto);
    return ResponseEntity.ok(response);
}


    @GetMapping
    public ResponseEntity<Response<List<PatientDto>>> getAllPatients() {
        List<PatientDto> patients = patientService.getAllPatients()
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
        Response<List<PatientDto>> response = Response.successfulResponse("All patients fetched successfully", patients);
        return ResponseEntity.ok(response);
    }


@PutMapping("/{patient_id}")
public ResponseEntity<Response<PatientDto>> updatePatient(@PathVariable("patient_id") Long patient_id, @RequestBody PatientDto patientDto) {
    Patient updatedPatient = patientService.updatePatient(patient_id, patientMapper.toEntity(patientDto));
    PatientDto responseDto = patientMapper.toDto(updatedPatient);
    Response<PatientDto> response = Response.successfulResponse("Patient updated successfully", responseDto);
    return ResponseEntity.ok(response);
}

@DeleteMapping("/{patient_id}")
public ResponseEntity<Response<Void>> deletePatient(@PathVariable("patient_id") Long patient_id) {
    patientService.deletePatient(patient_id);
    Response<Void> response = Response.successfulResponse("Patient deleted successfully", null);
    return ResponseEntity.ok(response);
}

//    @GetMapping("/csrf-token")
//    public Object getCsrftoken(HttpServletRequest request){
//        return request.getAttribute("_csrf");
//    }
}
