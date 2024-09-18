package com.example.HospitalManagement.Repository;

import com.example.HospitalManagement.Entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long> {
}
