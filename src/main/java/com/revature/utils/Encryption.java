package com.revature.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    /* private constructor to will make this class
       un-instantiable since we only have static methods. */
    private Encryption(){};

    public static String stringToMD5(String s) {
        final byte[] defaultBytes = s.getBytes();
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(defaultBytes);
            final byte[] messageDigest = md5MsgDigest.digest();

            final StringBuilder hexString = new StringBuilder();
            for (final byte element : messageDigest) {
                final String hex = Integer.toHexString(0xFF & element);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            s = hexString + "";
        } catch (final NoSuchAlgorithmException nsa) {
            nsa.printStackTrace();
        }
        return s;
    }
}