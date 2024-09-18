package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.dto.FileUploadDto;

import java.util.List;

public interface FileUploadService {
    FileUploadDto createFileUpload(FileUploadDto fileUploadDto);
    FileUploadDto getFileUploadById(Long id);
    List<FileUploadDto> getAllFileUploads();
    FileUploadDto updateFileUpload(Long id, FileUploadDto fileUploadDto);
    void deleteFileUpload(Long id);
}
