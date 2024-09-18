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
public class BillDto {
    private Long billId;
    private String amount;
    private Date billDate;
    private String description;
    private Long patientId;  // Reference to the patient
}

