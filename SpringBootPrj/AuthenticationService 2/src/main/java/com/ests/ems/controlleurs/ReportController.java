package com.ests.ems.controlleurs;

import com.ests.ems.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;

@Controller  // Use @Controller for rendering views
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // This method should return the HTML page
    @GetMapping
    public String showReportPage() {
        return "report";  // Returns 'report.html' (Thymeleaf template)
    }

    // Generate PDF Report
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdfReport() {
        ByteArrayInputStream pdfStream = reportService.exportPdfReport();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=employee_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes());
    }

    // Generate Excel Report
    @GetMapping("/excel")
    public ResponseEntity<byte[]> generateExcelReport() {
        ByteArrayInputStream excelStream = reportService.exportExcelReport();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=employee_report.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelStream.readAllBytes());
    }
}
