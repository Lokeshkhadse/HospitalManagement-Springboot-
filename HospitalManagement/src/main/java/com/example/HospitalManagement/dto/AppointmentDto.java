package com.example.HospitalManagement.dto;

import com.example.HospitalManagement.Entity.Appointment;
import com.example.HospitalManagement.Entity.Doctor;
import com.example.HospitalManagement.Entity.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@NoArgsConstructor
public class AppointmentDto {
    private Long appointment_id;
    private Date appointmentDate;
    private String reason;
    private Long patientId;
    private Long doctorId;

    public AppointmentDto(Long appointment_id, Date appointmentDate, String reason, Long patientId, Long doctorId) {
        this.appointment_id = appointment_id;
        this.appointmentDate = appointmentDate;
        this.reason = reason;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

}
