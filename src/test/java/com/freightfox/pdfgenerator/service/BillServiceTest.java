package com.freightfox.pdfgenerator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;

@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

    @InjectMocks
    private BillService billService;
    
    @Test
    public void responseHeadersTest() {
        String fileName = "bill.pdf";
        HttpHeaders headers = billService.responseHeaders(fileName);

        assertNotNull(headers);
        assertEquals(1, headers.size());;
        assertEquals("attachment ; filename = \"bill.pdf\"", headers.getFirst(HttpHeaders.CONTENT_DISPOSITION));
    }
}
