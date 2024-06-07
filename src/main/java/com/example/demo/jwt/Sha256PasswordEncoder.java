package com.example.demo.jwt;

import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Sha256PasswordEncoder implements PasswordEncoder {

    private final Log logger;

    public Sha256PasswordEncoder() {
        this.logger = LogFactory.getLog(this.getClass());
    }

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            String fog = "Peq" + rawPassword + "4vS";
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(fog.getBytes());
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        } else if (encodedPassword != null && encodedPassword.length() != 0) {
            if (!encode(rawPassword).equals(encodedPassword)) {
                this.logger.warn("Encoded password does not look like BCrypt");
                return false;
            } else {
                return encode(rawPassword).equals(encodedPassword);
            }
        } else {
            this.logger.warn("Empty encoded password");
            return false;
        }


    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}