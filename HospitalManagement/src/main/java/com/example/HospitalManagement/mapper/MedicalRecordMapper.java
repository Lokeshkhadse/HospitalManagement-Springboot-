package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.MedicalRecord;
import com.example.HospitalManagement.dto.MedicalRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MedicalRecordMapper {

    @Mapping(source = "patient.patient_id", target = "patientId")
    @Mapping(source = "doctor.doctor_id", target = "doctorId")
    MedicalRecordDto toDTO(MedicalRecord medicalRecord);

    @Mapping(source = "patientId", target = "patient.patient_id")
    @Mapping(source = "doctorId", target = "doctor.doctor_id")
    MedicalRecord toEntity(MedicalRecordDto medicalRecordDto);

    @Mapping(target = "medicalrecord_id", ignore = true)
    @Mapping(source = "patientId", target = "patient.patient_id")
    @Mapping(source = "doctorId", target = "doctor.doctor_id")
    void updateEntityFromDTO(MedicalRecordDto medicalRecordDto, @MappingTarget MedicalRecord medicalRecord);
}
