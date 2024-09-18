package com.example.HospitalManagement.controller;


import com.example.HospitalManagement.CommonResponse.Response;
import com.example.HospitalManagement.dto.OperationDto;
import com.example.HospitalManagement.Service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;



    @PostMapping
    public ResponseEntity<Response<OperationDto>> createOperation(@RequestBody OperationDto operationDto) {
        OperationDto createdOperation = operationService.createOperation(operationDto);
        Response<OperationDto> response = Response.successfulResponse("Operation created successfully", createdOperation);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{operation_id}")
    public ResponseEntity<Response<OperationDto>> getOperationById(@PathVariable("operation_id") Long operation_id) {
        OperationDto operationDto = operationService.getOperationById(operation_id);
        Response<OperationDto> response = Response.successfulResponse("Operation fetched successfully", operationDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<OperationDto>>> getAllOperations() {
        List<OperationDto> operations = operationService.getAllOperations();
        Response<List<OperationDto>> response = Response.successfulResponse("All operations fetched successfully", operations);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{operation_id}")
    public ResponseEntity<Response<OperationDto>> updateOperation(@PathVariable("operation_id") Long operation_id, @RequestBody OperationDto operationDto) {
        OperationDto updatedOperation = operationService.updateOperation(operation_id, operationDto);
        Response<OperationDto> response = Response.successfulResponse("Operation updated successfully", updatedOperation);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{operation_id}")
    public ResponseEntity<Response<Void>> deleteOperation(@PathVariable("operation_id") Long operation_id) {
        operationService.deleteOperation(operation_id);
        Response<Void> response = Response.successfulResponse("Operation deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}

