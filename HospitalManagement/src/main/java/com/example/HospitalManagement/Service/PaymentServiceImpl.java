package com.example.HospitalManagement.Service;


import com.example.HospitalManagement.Entity.Bill;
import com.example.HospitalManagement.Entity.Patient;
import com.example.HospitalManagement.Entity.Payment;
import com.example.HospitalManagement.Exception.ResourceNotFoundException;
import com.example.HospitalManagement.Repository.PatientRepository;
import com.example.HospitalManagement.Repository.PaymentRepository;
import com.example.HospitalManagement.mapper.PaymentMapper;

import com.example.HospitalManagement.dto.PaymentDto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = paymentMapper.toEntity(paymentDto);
        if (paymentDto.getPatientId() != null) {
            Patient patient = patientRepository.findById(paymentDto.getPatientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + paymentDto.getPatientId()));
            payment.setPatient(patient);
        }
        Payment savedPayment = paymentRepository.save(payment);

        // Send the email after payment is successfully saved
        sendPaymentReceiptEmail(savedPayment);
        return paymentMapper.toDto(savedPayment);
    }


    @Override
    public PaymentDto updatePayment(Long id, PaymentDto paymentDto) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID: " + id));

        paymentMapper.updateEntityFromDTO(paymentDto, existingPayment);

        if (paymentDto.getPatientId() != null) {
            Patient patient = patientRepository.findById(paymentDto.getPatientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + paymentDto.getPatientId()));
            existingPayment.setPatient(patient);
        }
        Payment updatedPayment = paymentRepository.save(existingPayment);
        return paymentMapper.toDto(updatedPayment);
    }


    @Override
    public PaymentDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID: " + id));
        return paymentMapper.toDto(payment);
    }


    @Override
    public List<PaymentDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return  payments.stream().map((payment) -> paymentMapper.toDto(payment)).collect(Collectors.toList());
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public ByteArrayInputStream generatePaymentReceipt(Long paymentId) throws DocumentException {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Not Found"));

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12);

        document.add(new Paragraph("Payment Receipt", font));
        document.add(new Paragraph("Payment Id: " + payment.getPayment_id(), font));
        document.add(new Paragraph("Payment Date: " + payment.getPaymentDate(), font));
        document.add(new Paragraph("Payment method: " + payment.getPaymentMethod(), font));
        document.add(new Paragraph("Payment Amount: " + payment.getAmountPaid(), font));
        document.add(new Paragraph("Patient ID: " + payment.getPatient().getPatient_id(), font));

        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public void sendPaymentReceiptEmail(Payment payment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);

        message.setTo("shreyashtidke.eidiko@gmail.com");
        message.setSubject("Payment Receipt");

        // Set the text content of the email
        message.setText("Dear " + payment.getPatient().getName() + ",\n\n" +
                "Thank you for your payment. Here are the details:\n" +
                "Payment ID: " + payment.getPayment_id() + "\n" +
                "Payment Date: " + payment.getPaymentDate() + "\n" +
                "Payment Method: " + payment.getPaymentMethod() + "\n" +
                "Amount Paid: " + payment.getAmountPaid() + "\n\n" +
                "Best regards,\n" +
                "Your Hospital");
            mailSender.send(message);

    }

}

