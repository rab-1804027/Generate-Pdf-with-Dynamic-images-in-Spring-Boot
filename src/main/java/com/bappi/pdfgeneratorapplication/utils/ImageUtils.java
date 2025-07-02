package com.bappi.pdfgeneratorapplication.utils;

import java.io.InputStream;

public class ImageUtils {

    public static InputStream getImageStream(int imageId){
        String image = "dsi"+imageId+".jpeg";
        return ImageUtils.class.getResourceAsStream("/static/images/"+image);
    }
}
