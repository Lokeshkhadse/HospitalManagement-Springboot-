package com.example.HospitalManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long doctor_id;

    private String name;
    private String specialization;
    private String contactNo;
    private String email;

    @OneToMany(mappedBy = "doctor" , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    @OneToMany(mappedBy = "doctor" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<MedicalRecord> medicalRecords;


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operation> operations;

}
