package com.bappi.pdfgeneratorapplication.controller;

import com.bappi.pdfgeneratorapplication.dto.CompanyInfoRequestDto;
import com.bappi.pdfgeneratorapplication.service.PdfService;
import com.itextpdf.text.DocumentException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PdfGeneratorController {

    private final PdfService pdfService;

    public PdfGeneratorController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/itext/{imageId}")
    public ResponseEntity<byte[]> generateItextPdf(@PathVariable int imageId) throws DocumentException, IOException {
        byte[] pdf = pdfService.generatePdfUsingItext(imageId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-Disposition", "inline; filename=itext.pdf")
                .body(pdf);
    }

    @GetMapping("/pdfbox/{imageId}")
    public ResponseEntity<byte[]> generateApachePdfBox(@PathVariable int imageId, @RequestBody CompanyInfoRequestDto companyInfoRequestDto) throws DocumentException, IOException {
        byte[] pdf = pdfService.generatePdfUsingApachePdfbox(imageId, companyInfoRequestDto);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-Disposition", "inline; filename=apache.pdf")
                .body(pdf);
    }
}
