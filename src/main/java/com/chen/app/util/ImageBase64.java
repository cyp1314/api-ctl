package com.chen.app.util;

public class ImageBase64 {
    public static Integer imageSize(String imageBase64Str){

        Integer equalIndex= imageBase64Str.indexOf("=");
        if(imageBase64Str.indexOf("=")>0) {
            imageBase64Str=imageBase64Str.substring(0, equalIndex);
        }
        Integer strLength=imageBase64Str.length();
        System.out.println("imageBase64Str Length = "+strLength);
        Integer size=strLength-(strLength/8)*2;
        return size;
    }
}
