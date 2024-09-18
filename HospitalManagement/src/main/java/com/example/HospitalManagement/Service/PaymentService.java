package com.example.HospitalManagement.Service;


import com.example.HospitalManagement.Entity.Payment;
import com.example.HospitalManagement.dto.PaymentDto;
import com.itextpdf.text.DocumentException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface PaymentService {
    PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto updatePayment(Long id, PaymentDto paymentDto);
    PaymentDto getPaymentById(Long id);
    List<PaymentDto> getAllPayments();
    void deletePayment(Long id);


    ByteArrayInputStream generatePaymentReceipt(Long paymentId) throws DocumentException;

     public void sendPaymentReceiptEmail(Payment payment);
}
