package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Appointment;
import com.example.HospitalManagement.dto.AppointmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(source = "patient.patient_id", target = "patientId")
    @Mapping(source = "doctor.doctor_id", target = "doctorId")
    AppointmentDto toDTO(Appointment appointment);

    @Mapping(source = "patientId", target = "patient.patient_id")
    @Mapping(source = "doctorId", target = "doctor.doctor_id")
    Appointment toEntity(AppointmentDto appointmentDto);

    @Mapping(target = "appointment_id", ignore = true)
    @Mapping(source = "patientId", target = "patient.patient_id")
    @Mapping(source = "doctorId", target = "doctor.doctor_id")
    void updateEntityFromDTO(AppointmentDto appointmentDto, @MappingTarget Appointment appointment);
}
