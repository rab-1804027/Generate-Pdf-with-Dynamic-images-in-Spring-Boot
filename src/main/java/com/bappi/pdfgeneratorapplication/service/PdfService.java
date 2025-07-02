package com.bappi.pdfgeneratorapplication.service;

import com.bappi.pdfgeneratorapplication.utils.ImageUtils;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PdfService {

    public byte[] integratingImageToPdfUsingItextLibrary(Integer imageId) throws DocumentException, IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfReader pdfReader = new PdfReader(getClass().getResourceAsStream("/test.pdf"));
        PdfStamper pdfStamper = new PdfStamper(pdfReader, outputStream);

        InputStream imageStream = ImageUtils.getImageStream(imageId);
        Image image = Image.getInstance(imageStream.readAllBytes());
        image.scaleToFit(100, 100);
        image.setAbsolutePosition(50, 720);
        pdfStamper.getOverContent(1).addImage(image);

        pdfStamper.close();
        pdfReader.close();

        return outputStream.toByteArray();

    }

    public byte[] integratingImageToPdfUsingApachePdfboxLibrary(Integer imageId) throws IOException {

        InputStream pdfInputStream = getClass().getResourceAsStream("/test.pdf");
        PDDocument document = PDDocument.load(pdfInputStream);

        InputStream imageStream = ImageUtils.getImageStream(imageId);
        PDImageXObject image = PDImageXObject.createFromByteArray(document, imageStream.readAllBytes(), "image");

        PDPage page = document.getPage(0);

        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

        contentStream.drawImage(image, 450, 720, 100, 100);

        contentStream.close();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        document.close();

        return outputStream.toByteArray();

    }
}
