package com.example.HospitalManagement.Repository;

import com.example.HospitalManagement.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment , Long> {
}
