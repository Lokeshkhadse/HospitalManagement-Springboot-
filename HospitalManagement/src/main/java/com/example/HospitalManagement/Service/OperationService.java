package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.dto.OperationDto;

import java.util.List;

public interface OperationService {
    OperationDto createOperation(OperationDto operationDto);

    OperationDto getOperationById(Long operation_id);

    List<OperationDto> getAllOperations();

    OperationDto updateOperation(Long operation_id, OperationDto operationDto);

    void deleteOperation(Long operation_id);
}
