package com.ests.ems.services;

import com.ests.ems.entities.Employee;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PDFGenerator {

    public static ByteArrayInputStream employeesToPDF(List<Employee> employees) {
        // Create a document and output stream
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Initialize PDF writer
            PdfWriter.getInstance(document, out);
            document.open();

            // Add title to the document
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Employee Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            document.add(title);

            // Create table with 5 columns
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 3, 3, 4, 3});

            // Define Table Header
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            String[] headers = {"ID", "First Name", "Last Name", "Email", "Department"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(240, 240, 240));
                table.addCell(cell);
            }

            // Populate Table Rows
            for (Employee employee : employees) {
                table.addCell(String.valueOf(employee.getId()));
                table.addCell(employee.getFirstName());
                table.addCell(employee.getLastName());
                table.addCell(employee.getEmail());
                table.addCell(employee.getDepartment().getName());
            }

            // Add table to the document
            document.add(table);
            document.close();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException e) {
            throw new RuntimeException("Error generating PDF report: " + e.getMessage());
        }
    }
}