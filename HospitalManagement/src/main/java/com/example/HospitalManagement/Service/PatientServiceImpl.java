package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.PatientRepository;
import com.example.HospitalManagement.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientMapper patientMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public Patient createPatient(Patient patient) {
        // Encode the password
       patient.setPassword(encoder.encode(patient.getPassword()));

        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long patient_id) {
        return  patientRepository.findById(patient_id).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

//    @Override
//    public Patient updatePatient(Long patient_id, Patient patient) {
//        if(patientRepository.existsById(patient_id)){
//            patient.setPatient_id(patient_id);
//            return patientRepository.save(patient);
//        }
//        return null;
//    }
@Override
public Patient updatePatient(Long patient_id, Patient patient) {
    if (patientRepository.existsById(patient_id)) {
        Patient existingPatient = patientRepository.findById(patient_id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patient_id));
        patientMapper.updateEntityFromDto(patientMapper.toDto(patient), existingPatient);
        return patientRepository.save(existingPatient);
    }
    throw new ResourceNotFoundException("Patient not found with id: " + patient_id);
}

    @Override
    public void deletePatient(Long patient_id) {
        patientRepository.deleteById(patient_id);
    }

}
