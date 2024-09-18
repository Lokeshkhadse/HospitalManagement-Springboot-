package com.example.HospitalManagement.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadDto {
    private Long id;
    private String filename;
    private String filecontent;
    private LocalDateTime uploadtime;
    private Long medicalRecordId;


}
