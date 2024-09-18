package com.example.HospitalManagement.Repository;

import com.example.HospitalManagement.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
