package com.example.HospitalManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String filename;

    private String filecontent;


    private LocalDateTime uploadtime;


    @ManyToOne
    @JoinColumn(name = "medicalrecord_id")
    private MedicalRecord medicalRecord;



}
