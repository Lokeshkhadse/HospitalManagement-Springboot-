package com.example.HospitalManagement.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Long paymentId;
    private double amountPaid;
    private Date paymentDate;
    private String paymentMethod;
    private Long patientId;  // Reference to the patient
}

