package com.example.HospitalManagement.Repository;

import com.example.HospitalManagement.Entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
    // Custom query methods (if needed)
}
