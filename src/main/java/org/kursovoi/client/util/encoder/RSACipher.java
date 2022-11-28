package org.kursovoi.client.util.encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.StandardCharsets;

@Component
public class RSACipher {

    private final Cipher cipher;

    @Autowired
    public RSACipher(Cipher cipher) {
        this.cipher = cipher;
    }

    private String cipher(String message) throws IllegalBlockSizeException, BadPaddingException {
        byte[] secretMessageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = cipher.doFinal(secretMessageBytes);
        return new String(encryptedMessageBytes, StandardCharsets.UTF_8);
    }
}
