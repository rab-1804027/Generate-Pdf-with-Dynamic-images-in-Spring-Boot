package com.bappi.pdfgeneratorapplication.utils;

import java.io.InputStream;

public class ImageUtils {

    public static InputStream getImageStream(String companyShortName, int imageId){
        String image = companyShortName+imageId+".jpeg";
        return ImageUtils.class.getResourceAsStream("/static/images/"+image);
    }
}
