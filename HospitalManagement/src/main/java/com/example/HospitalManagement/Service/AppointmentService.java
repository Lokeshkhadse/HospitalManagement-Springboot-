package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {

   // Appointment createAppointment(Appointment appointment);
   AppointmentDto createAppointment(AppointmentDto appointmentDto);


   // Appointment getAppointmentById(Long id);
   AppointmentDto getAppointmentById(Long appointment_id);

//    List<Appointment> getAllAppointment();
      List<AppointmentDto> getAllAppointment();

    //Appointment updateAppointment(Long id , Appointment appointment);
    AppointmentDto updateAppointment(Long appointment_id, AppointmentDto appointmentDto);

    void deleteAppointment(Long appointment_id);



}
