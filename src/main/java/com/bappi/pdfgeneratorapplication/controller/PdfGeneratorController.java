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
    public ResponseEntity<byte[]> GenerateItextPdf(@PathVariable int imageId, @RequestBody CompanyInfoRequestDto companyInfoRequestDto) throws DocumentException, IOException {
        byte[] pdf = pdfService.generateItextPdf(imageId, companyInfoRequestDto);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header("Content-Disposition", "inline; filename=itext.pdf")
                .body(pdf);
    }
}
