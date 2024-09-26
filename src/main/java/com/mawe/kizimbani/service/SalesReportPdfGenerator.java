package com.mawe.kizimbani.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.mawe.kizimbani.entity.SalesReport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class SalesReportPdfGenerator {
    public byte[] generatePdf(List<SalesReport> salesReports) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(bos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Add a title to the report
        document.add(new Paragraph("Sales Report"));

        // Create a table to display the sales report data
        Table table = new Table(new float[] { 1, 2, 2, 2, 2, 2, 2 });
        table.addCell("Order ID");
        table.addCell("Order Full Name");
        table.addCell("Order Full Order");
        table.addCell("Order Date");
        table.addCell("Order Contact Number");
        table.addCell("Order Status");
        table.addCell("Order Amount");
        table.addCell("Transaction ID");

        for (SalesReport salesReport : salesReports) {
            table.addCell(String.valueOf(salesReport.getOrderId()));
            table.addCell(salesReport.getOrderFullName());
            table.addCell(salesReport.getOrderFullOrder());
            table.addCell(salesReport.getOrderDate().toString());
            table.addCell(salesReport.getOrderContactNumber());
            table.addCell(salesReport.getOrderStatus());
            table.addCell(String.valueOf(salesReport.getOrderAmount()));
            table.addCell(salesReport.getTransactionId());
        }

        document.add(table);

        document.close();
        return bos.toByteArray();
    }
}
