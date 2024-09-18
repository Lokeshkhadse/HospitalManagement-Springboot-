package com.example.HospitalManagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OperationDto {
    private Long operation_id;
    private Date operationDate;
    private String details;

    private Long patientId;
    private Long doctorId;

    public OperationDto(Long operation_id, Date operationDate, String details, Long patientId, Long doctorId) {
        this.operation_id = operation_id;
        this.operationDate = operationDate;
        this.details = details;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }
}
