package com.example.demo;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHA256 {

    public String encrypt(String plainText) throws NoSuchAlgorithmException {
        String fog = "Peq" + plainText + "4vS";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(fog.getBytes());
        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
