package com.bappi.pdfgeneratorapplication.utils;

import java.io.InputStream;

public class PdfUtils {
    public static InputStream getPdfStream(){
        return PdfUtils.class.getResourceAsStream("/test.pdf");
    }

}
