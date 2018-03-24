package com.ushier.hospital.illness.web.util;

import java.security.MessageDigest;

public class MD5Encoder {
    public static String encode(String s) {
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(
                    s.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args){
        String s = "admin";
        String result = encode(s);
        System.out.println(result);
        System.out.println(result.length());
    }

}
