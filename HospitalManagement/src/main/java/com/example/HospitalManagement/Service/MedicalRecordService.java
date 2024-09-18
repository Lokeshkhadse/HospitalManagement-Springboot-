package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.dto.MedicalRecordDto;
import java.util.List;

public interface MedicalRecordService {

        MedicalRecordDto createMedicalRecord(MedicalRecordDto medicalRecordDto);

        MedicalRecordDto getMedicalRecordByRecordId(Long medicalrecord_id);

        List<MedicalRecordDto> getAllMedicalRecords();

        MedicalRecordDto updateMedicalRecord(Long medicalrecord_id, MedicalRecordDto medicalRecordDto);

        void deleteRecord(Long medicalrecord_id);
}
