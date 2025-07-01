package com.bappi.pdfgeneratorapplication.service;

import com.bappi.pdfgeneratorapplication.dto.CompanyInfoRequestDto;
import com.bappi.pdfgeneratorapplication.utils.ImageUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PdfService {

    public byte[] generateItextPdf(Integer imageId, CompanyInfoRequestDto companyInfoRequestDto) throws DocumentException, IOException {

        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, outputStream);

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph title = new Paragraph(companyInfoRequestDto.name(), font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Paragraph address = new Paragraph(companyInfoRequestDto.address(), font);
        address.setAlignment(Element.ALIGN_CENTER);
        document.add(address);
        document.add(Chunk.NEWLINE);

        InputStream imageStream = ImageUtils.getImageStream(companyInfoRequestDto.companyShortName(),  imageId);

        if (imageStream == null) {
            throw new RuntimeException("Invalid CompanyShortName or ImageId");
        }

        Image image = Image.getInstance(imageStream.readAllBytes());
        image.setAlignment(Element.ALIGN_CENTER);
        image.scaleToFit(100, 100);
        document.add(image);

        document.close();

        return outputStream.toByteArray();
    }
}
