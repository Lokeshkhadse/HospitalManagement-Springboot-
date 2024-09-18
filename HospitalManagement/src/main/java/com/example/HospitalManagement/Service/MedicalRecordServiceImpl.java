package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.MedicalRecord;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.MedicalRecordRepository;
import com.example.HospitalManagement.Repository.PatientRepository;
import com.example.HospitalManagement.Repository.DoctorRepository;
import com.example.HospitalManagement.dto.MedicalRecordDto;
import com.example.HospitalManagement.mapper.MedicalRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private MedicalRecordMapper medicalRecordMapper;

    @Override
    public MedicalRecordDto createMedicalRecord(MedicalRecordDto medicalRecordDto) {
        MedicalRecord medicalRecord = medicalRecordMapper.toEntity(medicalRecordDto);
        MedicalRecord savedRecord = medicalRecordRepository.save(medicalRecord);
        return medicalRecordMapper.toDTO(savedRecord);
    }

    @Override
    public MedicalRecordDto getMedicalRecordByRecordId(Long medicalrecord_id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(medicalrecord_id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record not found with ID: " + medicalrecord_id));
        return medicalRecordMapper.toDTO(medicalRecord);
    }

    @Override
    public List<MedicalRecordDto> getAllMedicalRecords() {
        return medicalRecordRepository.findAll()
                .stream()
                .map(medicalRecordMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordDto updateMedicalRecord(Long medicalrecord_id, MedicalRecordDto medicalRecordDto) {
        MedicalRecord existingRecord = medicalRecordRepository.findById(medicalrecord_id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record not found with ID: " + medicalrecord_id));

        // Update fields from DTO to the existing entity using the mapper
        medicalRecordMapper.updateEntityFromDTO(medicalRecordDto, existingRecord);

        MedicalRecord updatedRecord = medicalRecordRepository.save(existingRecord);
        return medicalRecordMapper.toDTO(updatedRecord);
    }


    @Override
    public void deleteRecord(Long medicalrecord_id) {
        medicalRecordRepository.deleteById(medicalrecord_id);
    }
}
