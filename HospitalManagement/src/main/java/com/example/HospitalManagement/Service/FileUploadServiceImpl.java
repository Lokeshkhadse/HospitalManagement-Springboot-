package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.FileUpload;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.FileUploadRepository;
import com.example.HospitalManagement.dto.FileUploadDto;
import com.example.HospitalManagement.mapper.FileUploadMapper;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Autowired
    private FileUploadMapper fileUploadMapper;



    @Override
    public FileUploadDto createFileUpload(FileUploadDto fileUploadDto) {
        FileUpload fileUpload = fileUploadMapper.toEntity(fileUploadDto);
        fileUpload.setUploadtime(LocalDateTime.now()); // Ensure uploadtime is set
        fileUpload = fileUploadRepository.save(fileUpload);
        return fileUploadMapper.toDto(fileUpload);
    }

    @Override
    public FileUploadDto getFileUploadById(Long id) {
        FileUpload fileUpload = fileUploadRepository.findById(id).orElseThrow();
        return fileUploadMapper.toDto(fileUpload);
    }

    @Override
    public List<FileUploadDto> getAllFileUploads() {
        return fileUploadRepository.findAll().stream()
                .map(fileUploadMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FileUploadDto updateFileUpload(Long id, FileUploadDto fileUploadDto) {
        if (!fileUploadRepository.existsById(id)) {
            throw new RuntimeException("FileUpload not found");
        }
        FileUpload fileUpload = fileUploadMapper.toEntity(fileUploadDto);
        fileUpload.setId(id);
        fileUpload.setUploadtime(LocalDateTime.now()); // Ensure uploadtime is updated
        fileUpload = fileUploadRepository.save(fileUpload);
        return fileUploadMapper.toDto(fileUpload);
    }

    @Override
    public void deleteFileUpload(Long id) {
        if (!fileUploadRepository.existsById(id)) {
            throw new RuntimeException("FileUpload not found");
        }
        fileUploadRepository.deleteById(id);
    }



}
