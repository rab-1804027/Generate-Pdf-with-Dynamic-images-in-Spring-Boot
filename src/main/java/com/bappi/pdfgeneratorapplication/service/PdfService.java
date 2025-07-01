package com.bappi.pdfgeneratorapplication.service;

import com.bappi.pdfgeneratorapplication.dto.CompanyInfoRequestDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generateItextPdf(Integer imageId, CompanyInfoRequestDto companyInfoRequestDto) throws DocumentException {

        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);

        Paragraph title = new Paragraph(companyInfoRequestDto.name(), font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        Paragraph address = new Paragraph(companyInfoRequestDto.address(), font);
        address.setAlignment(Element.ALIGN_CENTER);
        document.add(address);
        document.add(Chunk.NEWLINE);

        document.close();

        return outputStream.toByteArray();
    }
}
