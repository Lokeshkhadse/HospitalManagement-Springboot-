package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Patient;

import java.util.List;

public interface PatientService {

     Patient createPatient(Patient patient);

     Patient getPatientById(Long patient_id);

     List<Patient> getAllPatients();

     Patient updatePatient(Long patient_id, Patient patient);

     void deletePatient(Long patient_id);



}
