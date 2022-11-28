package org.kursovoi.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

@Configuration
public class EncryptionConfiguration {

    @Bean
    public PublicKey publicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, URISyntaxException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        File publicKeyFile = new File(getClass().getClassLoader().getResource("public.key").toURI());
        byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        return keyFactory.generatePublic(publicKeySpec);
    }

    @Bean
    public Cipher cipher(PublicKey publicKey) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return encryptCipher;
    }

}
