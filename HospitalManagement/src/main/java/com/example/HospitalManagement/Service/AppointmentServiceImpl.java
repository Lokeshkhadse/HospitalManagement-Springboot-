package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Appointment;
import com.example.HospitalManagement.Entity.Doctor;
import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.AppointmentRepository;
import com.example.HospitalManagement.Repository.DoctorRepository;
import com.example.HospitalManagement.Repository.PatientRepository;
import com.example.HospitalManagement.dto.AppointmentDto;
import com.example.HospitalManagement.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + appointmentDto.getPatientId()));
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + appointmentDto.getDoctorId()));

        Appointment appointment = appointmentMapper.toEntity(appointmentDto);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDTO(savedAppointment);
    }

    @Override
    public AppointmentDto getAppointmentById(Long appointment_id) {
        Appointment appointment = appointmentRepository.findById(appointment_id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + appointment_id));
        return appointmentMapper.toDTO(appointment);
    }

    @Override
    public List<AppointmentDto> getAllAppointment() {
        return appointmentRepository.findAll().stream()
                .map(appointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDto updateAppointment(Long appointment_id, AppointmentDto appointmentDto) {
        Appointment existingAppointment = appointmentRepository.findById(appointment_id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + appointment_id));

        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + appointmentDto.getPatientId()));
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + appointmentDto.getDoctorId()));

        appointmentMapper.updateEntityFromDTO(appointmentDto, existingAppointment);
        existingAppointment.setPatient(patient);
        existingAppointment.setDoctor(doctor);

        Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
        return appointmentMapper.toDTO(updatedAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
