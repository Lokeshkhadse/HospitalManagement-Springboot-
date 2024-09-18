package com.example.HospitalManagement.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="medical_records")
public class MedicalRecord {

      @Id
      @GeneratedValue(strategy = GenerationType.SEQUENCE)
      private Long medicalrecord_id;

      @Temporal(TemporalType.DATE)
      private Date recordDate;

      private String details;

      @ManyToOne
      @JoinColumn(name = "patient_id" )
      private Patient patient;

      @ManyToOne
      @JoinColumn(name="doctor_id")
      private Doctor doctor;



}
