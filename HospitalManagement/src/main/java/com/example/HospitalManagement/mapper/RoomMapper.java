package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.Entity.Room;
import com.example.HospitalManagement.dto.RoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "patientIds", source = "patients")
    RoomDto toDto(Room room);

    @Mapping(target = "patients", ignore = true)
    Room toEntity(RoomDto roomDto);

    @Mapping(target = "patients", ignore = true)
    void updateEntityFromDTO(RoomDto roomDto, @MappingTarget Room room);

    // Helper methods to convert lists if needed
    default List<Long> mapPatientsToPatientIds(List<Patient> patients) {
        if (patients == null) {
            return null;
        }
        return patients.stream()
                .map(Patient::getPatient_id)
                .collect(Collectors.toList());
    }

    default List<Patient> mapPatientIdsToPatients(List<Long> patientIds) {
        return new ArrayList<>();
    }
}
