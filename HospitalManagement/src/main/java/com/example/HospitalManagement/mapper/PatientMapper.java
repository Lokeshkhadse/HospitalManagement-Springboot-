package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.dto.PatientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    // Convert Patient entity to PatientDto
    PatientDto toDto(Patient patient);

    // Convert PatientDto to Patient entity
    Patient toEntity(PatientDto patientDto);

    // Update Patient entity from PatientDto
    @Mapping(target = "patient_id", ignore = true)  // Keep existing patient_id during updates
    void updateEntityFromDto(PatientDto patientDto, @MappingTarget Patient patient);
}
