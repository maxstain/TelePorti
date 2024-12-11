package org.example.teleporti.Utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordHash {
    static SecureRandom random = new SecureRandom();
    static byte[] salt = new byte[16];

    PasswordHash() {
    }

    public static String hashPassword(String mdp) throws NoSuchAlgorithmException, InvalidKeySpecException {
        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(mdp.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return new String(hash);
    }

    public static boolean checkPassword(String mdp, String hashedMdp) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return hashedMdp.equals(hashPassword(mdp));
    }
}
