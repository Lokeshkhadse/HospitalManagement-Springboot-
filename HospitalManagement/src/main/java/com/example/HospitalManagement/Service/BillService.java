package com.example.HospitalManagement.Service;

import com.example.HospitalManagement.dto.BillDto;
import com.itextpdf.text.DocumentException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface BillService {
    BillDto createBill(BillDto billDto);
    BillDto updateBill(Long id, BillDto billDto);
    BillDto getBillById(Long id);
    List<BillDto> getAllBills();
    void deleteBill(Long id);

    public ByteArrayInputStream generateBillReceipt(Long billId) throws DocumentException;
}
