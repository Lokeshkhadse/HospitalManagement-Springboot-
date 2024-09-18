package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Payment;
import com.example.HospitalManagement.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "patient.patient_id", target = "patientId")
    PaymentDto toDto(Payment payment);

    @Mapping(source = "patientId", target = "patient.patient_id")
    Payment toEntity(PaymentDto paymentDto);

    @Mapping(source = "patientId", target = "patient.patient_id")
    void updateEntityFromDTO(PaymentDto paymentDto, @MappingTarget Payment payment);
}

