package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.AadharCardPhoto;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.AadharCardPhotoRepository;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;

@Service
public class AadharCardPhotoService {

    @Autowired
    private AadharCardPhotoRepository aadharCardPhotoRepository;

    @Autowired
    private PDFService pdfService;

    @Autowired
    private  OCRService ocrService;


    public AadharCardPhoto savePhoto(Long patientId, byte[] photo) {
        if (patientId == null || photo == null) {
            throw new IllegalArgumentException("Patient ID and photo must not be null");
        }

        AadharCardPhoto aadharCardPhoto = new AadharCardPhoto();
        aadharCardPhoto.setPatientId(patientId);
        aadharCardPhoto.setPhoto(photo);
        return aadharCardPhotoRepository.save(aadharCardPhoto);
    }

    //img read + pdf
    public String getAadhaarCardData(Long id) throws IOException, TesseractException {
        AadharCardPhoto aadharCardPhoto = aadharCardPhotoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        String aadhaarData = null;

        if (aadharCardPhoto.getPhoto() != null) {
            // Decode the Base64 encoded string to bytes
            byte[] photoBytes = aadharCardPhoto.getPhoto();

            if (isPDF(photoBytes)) {
                // Create a MultipartFile for the PDF
                MultipartFile pdfFile = createMultipartFile(photoBytes, "aadhaar.pdf");
                aadhaarData = pdfService.extractTextFromPDF(pdfFile);
            } else {
                // Create a MultipartFile for the image
                MultipartFile imageFile = createMultipartFile(photoBytes, "aadhaar.jpg");
                aadhaarData = ocrService.extractTextFromImage(imageFile);
            }
        }

        return aadhaarData;
    }

    //pdf ahe ka nhi tyasathi check karoty
    private boolean isPDF(byte[] fileBytes) {
        // Simple check for PDF by its magic number
        return fileBytes.length > 4 && fileBytes[0] == 0x25 && fileBytes[1] == 0x50 &&
                fileBytes[2] == 0x44 && fileBytes[3] == 0x46; // %PDF
    }

    private MultipartFile createMultipartFile(byte[] fileBytes, String fileName) {
        return new MultipartFile() {
            @Override
            public String getName() {
                return fileName;
            }

            @Override
            public String getOriginalFilename() {
                return fileName;
            }

            @Override
            public String getContentType() {
                return null; // You can set content type if necessary
            }

            @Override
            public boolean isEmpty() {
                return fileBytes == null || fileBytes.length == 0;
            }

            @Override
            public long getSize() {
                return fileBytes.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return fileBytes;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(fileBytes);
            }



            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                Files.write(dest.toPath(), fileBytes);
            }
        };
    }

}
