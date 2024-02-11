package com.freightfox.pdfgenerator.service;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.freightfox.pdfgenerator.pojo.Bill;
import com.itextpdf.html2pdf.HtmlConverter;

@Service
public class BillService {
    public byte[] html2pdf(String html) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(html, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public HttpHeaders responseHeaders(String fileName) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment ; filename = " + "\"" + fileName + "\"");
        return httpHeaders;
    }

    public String fileName(Bill bill) {
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(UUID.randomUUID().toString());
        fileNameBuilder.append(".pdf");
        return fileNameBuilder.toString();
    }
}
