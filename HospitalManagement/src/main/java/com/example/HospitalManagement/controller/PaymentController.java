package com.example.HospitalManagement.controller;


import com.example.HospitalManagement.CommonResponse.Response;
import com.example.HospitalManagement.Entity.Payment;
import com.example.HospitalManagement.dto.PaymentDto;
import com.example.HospitalManagement.Service.PaymentService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private JavaMailSender mailSender;

//    @PostMapping
//    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
//        PaymentDto createdPayment = paymentService.createPayment(paymentDto);
////        Payment payment = paymentService.getPaymentById(createdPayment.getPaymentId());
////        if (payment != null) {
////            // Send the email after payment creation
////            paymentService.sendPaymentReceiptEmail(payment);
////        }
//        return new ResponseEntity<>(createdPayment, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{payment_id}")
//    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable("payment_id") Long payment_id) {
//        PaymentDto paymentDto = paymentService.getPaymentById(payment_id);
//        return ResponseEntity.ok(paymentDto);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<PaymentDto>> getAllPayments() {
//        List<PaymentDto> payments = paymentService.getAllPayments();
//        return ResponseEntity.ok(payments);
//    }
//
//    @PutMapping("/{payment_id}")
//    public ResponseEntity<PaymentDto> updatePayment(@PathVariable("payment_id") Long payment_id, @RequestBody PaymentDto paymentDto) {
//        PaymentDto updatedPayment = paymentService.updatePayment(payment_id, paymentDto);
//        return ResponseEntity.ok(updatedPayment);
//    }
//
//    @DeleteMapping("/{payment_id}")
//    public ResponseEntity<Void> deletePayment(@PathVariable("payment_id") Long payment_id) {
//        paymentService.deletePayment(payment_id);
//        return ResponseEntity.noContent().build();
//    }
//
//
//    @GetMapping("/download/{paymentId}")
//    public ResponseEntity<byte[]> downloadPaymentReceipt(@PathVariable Long paymentId) throws DocumentException, IOException {
//        ByteArrayInputStream receiptStream = paymentService.generatePaymentReceipt(paymentId);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=payment_receipt.pdf");
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(receiptStream.readAllBytes());
//    }


    @PostMapping
    public ResponseEntity<Response<PaymentDto>> createPayment(@RequestBody PaymentDto paymentDto) {
        PaymentDto createdPayment = paymentService.createPayment(paymentDto);
        Response<PaymentDto> response = Response.successfulResponse("Payment created successfully", createdPayment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{payment_id}")
    public ResponseEntity<Response<PaymentDto>> getPaymentById(@PathVariable("payment_id") Long payment_id) {
        PaymentDto paymentDto = paymentService.getPaymentById(payment_id);
        Response<PaymentDto> response = Response.successfulResponse("Payment fetched successfully", paymentDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<PaymentDto>>> getAllPayments() {
        List<PaymentDto> payments = paymentService.getAllPayments();
        Response<List<PaymentDto>> response = Response.successfulResponse("All payments fetched successfully", payments);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{payment_id}")
    public ResponseEntity<Response<PaymentDto>> updatePayment(@PathVariable("payment_id") Long payment_id, @RequestBody PaymentDto paymentDto) {
        PaymentDto updatedPayment = paymentService.updatePayment(payment_id, paymentDto);
        Response<PaymentDto> response = Response.successfulResponse("Payment updated successfully", updatedPayment);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{payment_id}")
    public ResponseEntity<Response<Void>> deletePayment(@PathVariable("payment_id") Long payment_id) {
        paymentService.deletePayment(payment_id);
        Response<Void> response = Response.successfulResponse("Payment deleted successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download/{paymentId}")
    public ResponseEntity<byte[]> downloadPaymentReceipt(@PathVariable Long paymentId) throws DocumentException, IOException {
        ByteArrayInputStream receiptStream = paymentService.generatePaymentReceipt(paymentId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=payment_receipt.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(receiptStream.readAllBytes());
    }


}
