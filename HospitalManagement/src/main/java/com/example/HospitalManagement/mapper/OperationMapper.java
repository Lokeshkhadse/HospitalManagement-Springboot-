package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Operation;
import com.example.HospitalManagement.dto.OperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    @Mapping(source = "patient.patient_id", target = "patientId")
    @Mapping(source = "doctor.doctor_id", target = "doctorId")
    OperationDto toDTO(Operation operation);

    @Mapping(source = "patientId", target = "patient.patient_id")
    @Mapping(source = "doctorId", target = "doctor.doctor_id")
    Operation toEntity(OperationDto operationDto);

    @Mapping(target = "operation_id", ignore = true) // Ignores the ID field when updating
    @Mapping(source = "patientId", target = "patient.patient_id")
    @Mapping(source = "doctorId", target = "doctor.doctor_id")
    void updateEntityFromDTO(OperationDto operationDto, @MappingTarget Operation operation);
}
