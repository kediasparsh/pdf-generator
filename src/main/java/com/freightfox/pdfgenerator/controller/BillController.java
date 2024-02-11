package com.freightfox.pdfgenerator.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.freightfox.pdfgenerator.pojo.Bill;
import com.freightfox.pdfgenerator.service.BillService;

@RestController
public class BillController {

    private static final String PDF_DIRECTORY = "C:\\Users\\spars\\Downloads";

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private BillService billService;

    @PostMapping("/bill")
    public ResponseEntity<InputStreamResource> generatePDF(@RequestBody Bill bill) throws IOException {
        String pdfName = billService.fileName(bill);
        Path pdfPath = Paths.get(PDF_DIRECTORY, pdfName);

        Map<String, Object> map = new HashMap<>();
        map.put("bill", bill);
        Context context = new Context();
        context.setVariables(map);
        String html = springTemplateEngine.process("bill", context);
        byte[] pdfBytes = billService.html2pdf(html);

        Files.createDirectories(pdfPath.getParent());
        Files.write(pdfPath, pdfBytes);

        InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(pdfBytes));
        return ResponseEntity.ok()
               .headers(billService.responseHeaders(pdfName))
               .contentType(MediaType.APPLICATION_PDF)
               .contentLength(pdfBytes.length)
               .body(inputStreamResource);
    }
}
