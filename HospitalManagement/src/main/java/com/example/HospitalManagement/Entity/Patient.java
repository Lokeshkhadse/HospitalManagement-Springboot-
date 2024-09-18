package com.example.HospitalManagement.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long patient_id;

    private String name;
    private Date dob;
    private String gender;
    private String contactNo;
    private String address;
    private String password;


    @OneToMany(mappedBy = "patient" , cascade = CascadeType.ALL,orphanRemoval = true)
    //@JsonManagedReference
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "patient" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<MedicalRecord> medicalRecords;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operation> operations;

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Bill> bills;
}
