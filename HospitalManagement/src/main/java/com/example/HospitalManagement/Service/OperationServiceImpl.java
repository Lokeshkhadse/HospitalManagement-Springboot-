package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Operation;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.DoctorRepository;
import com.example.HospitalManagement.Repository.OperationRepository;
import com.example.HospitalManagement.Repository.PatientRepository;
import com.example.HospitalManagement.dto.OperationDto;
import com.example.HospitalManagement.mapper.OperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private OperationMapper operationMapper;

    @Autowired
    private PatientRepository patientRepository; // Add this
    @Autowired
    private DoctorRepository doctorRepository;

//    @Override
//    public OperationDto createOperation(OperationDto operationDto) {
//        Operation operation = operationMapper.toEntity(operationDto);
//        Operation savedOperation = operationRepository.save(operation);
//        return operationMapper.toDTO(savedOperation);
//    }
//
//    @Override
//    public OperationDto getOperationById(Long operation_id) {
//        Operation operation = operationRepository.findById(operation_id)
//                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with ID: " + operation_id));
//        return operationMapper.toDTO(operation);
//    }
//
//    @Override
//    public List<OperationDto> getAllOperations() {
//        return operationRepository.findAll().stream()
//                .map(operationMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public OperationDto updateOperation(Long operation_id, OperationDto operationDto) {
//        Operation existingOperation = operationRepository.findById(operation_id)
//                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with ID: " + operation_id));
//
//        operationMapper.updateEntityFromDTO(operationDto, existingOperation);
//        Operation updatedOperation = operationRepository.save(existingOperation);
//        return operationMapper.toDTO(updatedOperation);
//    }
//
//    @Override
//    public void deleteOperation(Long operation_id) {
//        if (!operationRepository.existsById(operation_id)) {
//            throw new ResourceNotFoundException("Operation not found with ID: " + operation_id);
//        }
//        operationRepository.deleteById(operation_id);
//    }
@Override
public OperationDto createOperation(OperationDto operationDto) {
    // Validate patient and doctor existence
    if (!patientRepository.existsById(operationDto.getPatientId())) {
        throw new ResourceNotFoundException("Patient not found with ID: " + operationDto.getPatientId());
    }
    if (!doctorRepository.existsById(operationDto.getDoctorId())) {
        throw new ResourceNotFoundException("Doctor not found with ID: " + operationDto.getDoctorId());
    }

    // Map DTO to entity
    Operation operation = operationMapper.toEntity(operationDto);

    // Set Patient and Doctor entities
    operation.setPatient(patientRepository.findById(operationDto.getPatientId()).orElse(null));
    operation.setDoctor(doctorRepository.findById(operationDto.getDoctorId()).orElse(null));

    // Save operation
    Operation savedOperation = operationRepository.save(operation);
    return operationMapper.toDTO(savedOperation);
}

    @Override
    public OperationDto getOperationById(Long operation_id) {
        Operation operation = operationRepository.findById(operation_id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with ID: " + operation_id));
        return operationMapper.toDTO(operation);
    }

    @Override
    public List<OperationDto> getAllOperations() {
        return operationRepository.findAll().stream()
                .map(operationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OperationDto updateOperation(Long operation_id, OperationDto operationDto) {
        // Validate patient and doctor existence
        if (!patientRepository.existsById(operationDto.getPatientId())) {
            throw new ResourceNotFoundException("Patient not found with ID: " + operationDto.getPatientId());
        }
        if (!doctorRepository.existsById(operationDto.getDoctorId())) {
            throw new ResourceNotFoundException("Doctor not found with ID: " + operationDto.getDoctorId());
        }

        Operation existingOperation = operationRepository.findById(operation_id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with ID: " + operation_id));

        // Update the operation details
        operationMapper.updateEntityFromDTO(operationDto, existingOperation);

        // Set Patient and Doctor entities
        existingOperation.setPatient(patientRepository.findById(operationDto.getPatientId()).orElse(null));
        existingOperation.setDoctor(doctorRepository.findById(operationDto.getDoctorId()).orElse(null));

        // Save updated operation
        Operation updatedOperation = operationRepository.save(existingOperation);
        return operationMapper.toDTO(updatedOperation);
    }

    @Override
    public void deleteOperation(Long operation_id) {
        if (!operationRepository.existsById(operation_id)) {
            throw new ResourceNotFoundException("Operation not found with ID: " + operation_id);
        }
        operationRepository.deleteById(operation_id);
    }
}
