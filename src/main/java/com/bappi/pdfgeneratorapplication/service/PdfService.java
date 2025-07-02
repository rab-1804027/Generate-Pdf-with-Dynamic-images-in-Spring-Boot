package com.bappi.pdfgeneratorapplication.service;

import com.bappi.pdfgeneratorapplication.dto.CompanyInfoRequestDto;
import com.bappi.pdfgeneratorapplication.utils.ImageUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontFactory;
import org.apache.pdfbox.pdmodel.font.PDType1CFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PdfService {

    public byte[] generatePdfUsingItext(Integer imageId) throws DocumentException, IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfReader pdfReader = new PdfReader(getClass().getResourceAsStream("/test.pdf"));
        PdfStamper pdfStamper = new PdfStamper(pdfReader, outputStream);

        InputStream imageStream = ImageUtils.getImageStream(imageId);
        Image image = Image.getInstance(imageStream.readAllBytes());
        image.scaleToFit(100, 100);
        image.setAbsolutePosition(500, 720);
        pdfStamper.getOverContent(1).addImage(image);

        pdfStamper.close();
        pdfReader.close();

        return outputStream.toByteArray();

    }

    public byte[] generatePdfUsingApachePdfbox(Integer imageId, CompanyInfoRequestDto companyInfoRequestDto) throws DocumentException, IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_ROMAN,12);
        contentStream.endText();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        return outputStream.toByteArray();

    }
}
