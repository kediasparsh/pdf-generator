package com.freightfox.pdfgenerator.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.freightfox.pdfgenerator.pojo.Bill;
import com.freightfox.pdfgenerator.pojo.Item;
import com.freightfox.pdfgenerator.service.BillService;

@RunWith(MockitoJUnitRunner.class)
public class BillControllerTest {
    
    @Mock
    private SpringTemplateEngine springTemplateEngine;

    @Mock
    private BillService billService;

    @InjectMocks
    private BillController billController;

    @Test
    public void generatePDFTest() throws IOException {
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setName("Product 1");
        item.setQuantity("12 Nos");
        item.setRate(123.00);
        item.setAmount(1476.00);
        items.add(item);

        Bill bill = new Bill();
        bill.setSeller("XYZ Pvt. Ltd.");
        bill.setSellerGstin("29AABBCCDD121ZD");
        bill.setSellerAddress("New Delhi, India");
        bill.setBuyer("Vedant Computers");
        bill.setBuyerGstin("29AABBCCDD131ZD");
        bill.setBuyerAddress("New Delhi, India");
        bill.setItems(items);

        when(billService.fileName(bill)).thenReturn("bill.pdf");
        when(billService.html2pdf(null)).thenReturn(new byte[0]);

        ResponseEntity<InputStreamResource> response = billController.generatePDF(bill);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_PDF, response.getHeaders().getContentType());
    }
}
