package com.example.HospitalManagement.controller;

import com.example.HospitalManagement.CommonResponse.Response;
import com.example.HospitalManagement.Entity.AadharCardPhoto;
import com.example.HospitalManagement.Service.AadharCardPhotoService;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/aadharcardphoto")
public class AadharCardPhotoController {

    @Autowired
    private AadharCardPhotoService aadharCardPhotoService;

//    @PostMapping("/upload/{patientId}")
//    public ResponseEntity<String> uploadPhoto(@PathVariable Long patientId, @RequestParam("photo") MultipartFile photo) throws IOException {
//        if (patientId == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient ID must not be null");
//        }
//
//        byte[] photoBytes = photo.getBytes();
//        aadharCardPhotoService.savePhoto(patientId, photoBytes);
//        return ResponseEntity.status(HttpStatus.OK).body("Photo uploaded successfully");
//    }
//
//    @GetMapping("/userAadhaarData/{id}")
//    public ResponseEntity<String> getUserAadhaarData(@PathVariable Long id) {
//        try {
//            String aadhaarData = aadharCardPhotoService.getAadhaarCardData(id);
//            return new ResponseEntity<>(aadhaarData, HttpStatus.OK);
//        } catch (IOException | TesseractException e) {
//            return new ResponseEntity<>("Failed to extract Aadhaar data", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
@PostMapping("/upload/{patientId}")
public ResponseEntity<Response<String>> uploadPhoto(@PathVariable Long patientId, @RequestParam("photo") MultipartFile photo) throws IOException {
    if (patientId == null) {
        Response<String> response = Response.failedResponse("Patient ID must not be null");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    byte[] photoBytes = photo.getBytes();
    aadharCardPhotoService.savePhoto(patientId, photoBytes);
    Response<String> response = Response.successfulResponse("Photo uploaded successfully");
    return ResponseEntity.status(HttpStatus.OK).body(response);
}

    @GetMapping("/userAadhaarData/{id}")
    public ResponseEntity<Response<String>> getUserAadhaarData(@PathVariable Long id) {
        try {
            String aadhaarData = aadharCardPhotoService.getAadhaarCardData(id);
            Response<String> response = Response.successfulResponse("Aadhaar data fetched successfully", aadhaarData);
            return ResponseEntity.ok(response);
        } catch (IOException | TesseractException e) {
            Response<String> response = Response.failedResponse("Failed to extract Aadhaar data");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
