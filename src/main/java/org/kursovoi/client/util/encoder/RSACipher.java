package org.kursovoi.client.util.encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class RSACipher {

    private final Cipher cipher;

    @Autowired
    public RSACipher(Cipher cipher) {
        this.cipher = cipher;
    }

    public String cipher(String message) throws IllegalBlockSizeException, BadPaddingException {
        byte[] secretMessageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = cipher.doFinal(secretMessageBytes);
        return Base64.getEncoder().encodeToString(encryptedMessageBytes);
    }
}
