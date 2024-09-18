package com.example.HospitalManagement.controller;



import com.example.HospitalManagement.CommonResponse.Response;
import com.example.HospitalManagement.Service.AppointmentService;
import com.example.HospitalManagement.Service.AppointmentServiceImpl;
import com.example.HospitalManagement.dto.AppointmentDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
//
//    @PostMapping
//    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentDto appointmentDto) {
//        AppointmentDto createdAppointment = appointmentService.createAppointment(appointmentDto);
//        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{appointment_id}")
//    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable("appointment_id") Long appointment_id) {
//        AppointmentDto appointmentDto = appointmentService.getAppointmentById(appointment_id);
//        return ResponseEntity.ok(appointmentDto);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
//        List<AppointmentDto> appointments = appointmentService.getAllAppointment();
//        return ResponseEntity.ok(appointments);
//    }
//
//    @PutMapping("/{appointment_id}")
//    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable("appointment_id") Long appointment_id, @RequestBody AppointmentDto appointmentDto) {
//        AppointmentDto updatedAppointment = appointmentService.updateAppointment(appointment_id, appointmentDto);
//        return ResponseEntity.ok(updatedAppointment);
//    }
//
//    @DeleteMapping("/{appointment_id}")
//    public ResponseEntity<Void> deleteAppointment(@PathVariable("appointment_id") Long appointment_id) {
//        appointmentService.deleteAppointment(appointment_id);
//        return ResponseEntity.noContent().build();
//    }
@PostMapping
public ResponseEntity<Response<AppointmentDto>> createAppointment(@RequestBody AppointmentDto appointmentDto) {
    AppointmentDto createdAppointment = appointmentService.createAppointment(appointmentDto);
    Response<AppointmentDto> response = Response.successfulResponse("Appointment created successfully", createdAppointment);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
}

    @GetMapping("/{appointment_id}")
    public ResponseEntity<Response<AppointmentDto>> getAppointmentById(@PathVariable("appointment_id") Long appointment_id) {
        AppointmentDto appointmentDto = appointmentService.getAppointmentById(appointment_id);
        Response<AppointmentDto> response = Response.successfulResponse("Appointment fetched successfully", appointmentDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<AppointmentDto>>> getAllAppointments() {
        List<AppointmentDto> appointments = appointmentService.getAllAppointment();
        Response<List<AppointmentDto>> response = Response.successfulResponse("All appointments fetched successfully", appointments);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{appointment_id}")
    public ResponseEntity<Response<AppointmentDto>> updateAppointment(@PathVariable("appointment_id") Long appointment_id, @RequestBody AppointmentDto appointmentDto) {
        AppointmentDto updatedAppointment = appointmentService.updateAppointment(appointment_id, appointmentDto);
        Response<AppointmentDto> response = Response.successfulResponse("Appointment updated successfully", updatedAppointment);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{appointment_id}")
    public ResponseEntity<Response<Void>> deleteAppointment(@PathVariable("appointment_id") Long appointment_id) {
        appointmentService.deleteAppointment(appointment_id);
        Response<Void> response = Response.successfulResponse("Appointment deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
