package com.ests.ems.services;

import com.ests.ems.entities.Employee;
import com.ests.ems.repositories.EmployeeRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Export PDF Report
    public ByteArrayInputStream exportPdfReport() {
        List<Employee> employees = employeeRepository.findAll();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Add title
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Employee Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            // Create table
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 3, 3, 3, 3});

            // Add table headers
            Font headerFont = new Font(Font.HELVETICA, 12, Font.BOLD);
            table.addCell(new Phrase("ID", headerFont));
            table.addCell(new Phrase("First Name", headerFont));
            table.addCell(new Phrase("Last Name", headerFont));
            table.addCell(new Phrase("Email", headerFont));
            table.addCell(new Phrase("Department", headerFont));

            // Add employee data
            for (Employee emp : employees) {
                table.addCell(String.valueOf(emp.getId()));
                table.addCell(emp.getFirstName());
                table.addCell(emp.getLastName());
                table.addCell(emp.getEmail());
                table.addCell(emp.getDepartment().getName());
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException("Error while generating PDF report", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    // Export Excel Report
    public ByteArrayInputStream exportExcelReport() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Employees");

            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "First Name", "Last Name", "Email", "Department"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            int rowIdx = 1;
            for (Employee emp : employees) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(emp.getId());
                row.createCell(1).setCellValue(emp.getFirstName());
                row.createCell(2).setCellValue(emp.getLastName());
                row.createCell(3).setCellValue(emp.getEmail());
                row.createCell(4).setCellValue(emp.getDepartment().getName());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error while generating Excel report", e);
        }
    }
}
