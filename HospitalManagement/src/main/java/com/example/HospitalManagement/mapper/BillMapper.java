package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Bill;
import com.example.HospitalManagement.dto.BillDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BillMapper {
    @Mapping(source = "patient.patient_id", target = "patientId")
    BillDto toDto(Bill bill);

    @Mapping(source = "patientId", target = "patient.patient_id")
    Bill toEntity(BillDto billDto);

    @Mapping(source = "patientId", target = "patient.patient_id")
    void updateEntityFromDTO(BillDto billDto, @MappingTarget Bill bill);
}


