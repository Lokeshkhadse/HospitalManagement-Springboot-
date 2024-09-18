package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.Entity.Bill;
import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.BillRepository;
import com.example.HospitalManagement.Repository.PatientRepository;
import com.example.HospitalManagement.Service.BillService;
import com.example.HospitalManagement.mapper.BillMapper;

import com.example.HospitalManagement.dto.BillDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BillMapper billMapper;

    @Override
    public BillDto createBill(BillDto billDto) {

        Bill bill = billMapper.toEntity(billDto);
        if (billDto.getPatientId() != null) {
            Patient patient = patientRepository.findById(billDto.getPatientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + billDto.getPatientId()));
            bill.setPatient(patient);
        }
        Bill savedBill = billRepository.save(bill);
        return billMapper.toDto(savedBill);
    }

    @Override
    public BillDto updateBill(Long id, BillDto billDto) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found with ID: " + id));

        billMapper.updateEntityFromDTO(billDto, bill);
        if (billDto.getPatientId() != null) {
            Patient patient = patientRepository.findById(billDto.getPatientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + billDto.getPatientId()));
            bill.setPatient(patient);
        }
        Bill updatedBill = billRepository.save(bill);
        return billMapper.toDto(updatedBill);
    }


    @Override
    public BillDto getBillById(Long id) {
        return billRepository.findById(id)
                .map(billMapper::toDto)
                .orElse(null); // or throw an exception
    }

    @Override
    public List<BillDto> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        return  bills.stream().map((bill) -> billMapper.toDto(bill)).collect(Collectors.toList());

    }

    @Override
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public ByteArrayInputStream generateBillReceipt(Long billId) throws DocumentException {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking Not Found"));



        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document,out);

        document.open();
        Font font = new Font(Font.FontFamily.TIMES_ROMAN,12);

        document.add(new Paragraph("Bill Receipt",font));
        document.add(new Paragraph("Bill Id: " + bill.getBillId(),font));
        document.add(new Paragraph("Bill Date: " + bill.getBillDate(),font));
        document.add(new Paragraph("Bill Amount :"+ bill.getAmount(),font));
        document.add(new Paragraph("Bill Description: " + bill.getDescription(), font));
        document.add(new Paragraph("Patient ID: " + bill.getPatient().getPatient_id(), font));


        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }


}
