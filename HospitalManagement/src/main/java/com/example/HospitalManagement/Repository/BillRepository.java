package com.example.HospitalManagement.Repository;

import com.example.HospitalManagement.Entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
