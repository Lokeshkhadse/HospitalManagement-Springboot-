package com.example.HospitalManagement.mapper;

import com.example.HospitalManagement.Entity.FileUpload;
import com.example.HospitalManagement.dto.FileUploadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FileUploadMapper {
    FileUploadMapper INSTANCE = Mappers.getMapper(FileUploadMapper.class);


    @Mapping(source = "medicalRecord.medicalrecord_id", target = "medicalRecordId")
    FileUploadDto toDto(FileUpload fileUpload);

    @Mapping(source = "medicalRecordId", target = "medicalRecord.medicalrecord_id")
    FileUpload toEntity(FileUploadDto fileUploadDto);
}
