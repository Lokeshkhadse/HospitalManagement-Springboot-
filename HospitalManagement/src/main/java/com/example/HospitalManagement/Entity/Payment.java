package com.example.HospitalManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
 @Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long payment_id;


    private String amountPaid;

    @Temporal(TemporalType.DATE)

    private Date paymentDate;


    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;


}

