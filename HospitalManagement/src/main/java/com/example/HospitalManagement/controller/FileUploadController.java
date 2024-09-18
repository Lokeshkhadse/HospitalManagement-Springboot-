package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.CommonResponse.Response;
import com.example.HospitalManagement.Service.FileUploadService;
import com.example.HospitalManagement.dto.FileUploadDto;
import com.example.HospitalManagement.Entity.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/fileuploads")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

//    @PreAuthorize("hasRole('USER')")
//    @PostMapping("/upload")
//    public ResponseEntity<FileUploadDto> createFileUpload(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("medicalRecordId") Long medicalRecordId) throws IOException {
//        // Convert MultipartFile to FileUploadDto
//        FileUploadDto fileUploadDto = new FileUploadDto();
//        fileUploadDto.setFilename(file.getOriginalFilename());
//        fileUploadDto.setFilecontent(new String(file.getBytes())); // or use appropriate encoding
//        fileUploadDto.setMedicalRecordId(medicalRecordId);
//
//        // Save file upload information
//        FileUploadDto createdFileUpload = fileUploadService.createFileUpload(fileUploadDto);
//        return new ResponseEntity<>(createdFileUpload, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<FileUploadDto> getFileUploadById(@PathVariable("id") Long id) {
//        FileUploadDto fileUploadDto = fileUploadService.getFileUploadById(id);
//        return ResponseEntity.ok(fileUploadDto);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<FileUploadDto>> getAllFileUploads() {
//        List<FileUploadDto> fileUploads = fileUploadService.getAllFileUploads();
//        return ResponseEntity.ok(fileUploads);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<FileUploadDto> updateFileUpload(@PathVariable("id") Long id, @RequestBody FileUploadDto fileUploadDto) {
//        FileUploadDto updatedFileUpload = fileUploadService.updateFileUpload(id, fileUploadDto);
//        return ResponseEntity.ok(updatedFileUpload);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteFileUpload(@PathVariable("id") Long id) {
//        fileUploadService.deleteFileUpload(id);
//        return ResponseEntity.noContent().build();
//    }
@PreAuthorize("hasRole('USER')")
@PostMapping("/upload")
public ResponseEntity<Response<FileUploadDto>> createFileUpload(
        @RequestParam("file") MultipartFile file,
        @RequestParam("medicalRecordId") Long medicalRecordId) throws IOException {
    // Convert MultipartFile to FileUploadDto
    FileUploadDto fileUploadDto = new FileUploadDto();
    fileUploadDto.setFilename(file.getOriginalFilename());
    fileUploadDto.setFilecontent(new String(file.getBytes())); // or use appropriate encoding
    fileUploadDto.setMedicalRecordId(medicalRecordId);

    // Save file upload information
    FileUploadDto createdFileUpload = fileUploadService.createFileUpload(fileUploadDto);
    Response<FileUploadDto> response = Response.successfulResponse("File uploaded successfully", createdFileUpload);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
}

    @GetMapping("/{id}")
    public ResponseEntity<Response<FileUploadDto>> getFileUploadById(@PathVariable("id") Long id) {
        FileUploadDto fileUploadDto = fileUploadService.getFileUploadById(id);
        Response<FileUploadDto> response = Response.successfulResponse("File upload fetched successfully", fileUploadDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<FileUploadDto>>> getAllFileUploads() {
        List<FileUploadDto> fileUploads = fileUploadService.getAllFileUploads();
        Response<List<FileUploadDto>> response = Response.successfulResponse("All file uploads fetched successfully", fileUploads);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<FileUploadDto>> updateFileUpload(@PathVariable("id") Long id, @RequestBody FileUploadDto fileUploadDto) {
        FileUploadDto updatedFileUpload = fileUploadService.updateFileUpload(id, fileUploadDto);
        Response<FileUploadDto> response = Response.successfulResponse("File upload updated successfully", updatedFileUpload);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteFileUpload(@PathVariable("id") Long id) {
        fileUploadService.deleteFileUpload(id);
        Response<Void> response = Response.successfulResponse("File upload deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
