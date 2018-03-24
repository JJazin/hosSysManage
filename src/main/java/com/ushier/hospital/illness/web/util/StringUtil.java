package com.ushier.hospital.illness.web.util;


import java.util.UUID;

public class StringUtil {

    public static boolean isBlank(String s){
        return null == s || "".equals(s) ? true : false;
    }


    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "").trim();
    }
}
