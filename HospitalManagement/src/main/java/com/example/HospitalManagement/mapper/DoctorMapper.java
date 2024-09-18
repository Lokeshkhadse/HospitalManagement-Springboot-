package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Doctor;
import com.example.HospitalManagement.dto.DoctorDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorDto toDto(Doctor doctor);

    Doctor toEntity(DoctorDto doctorDto);

    void updateEntityFromDto(DoctorDto doctorDto, @MappingTarget Doctor doctor);
}

