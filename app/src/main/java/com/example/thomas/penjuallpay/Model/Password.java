package com.example.thomas.penjuallpay.Model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class Password {
    private String OriginPass;

    public Password(String pass){
        OriginPass = pass;
    }

    public String sha256() throws NoSuchAlgorithmException {
        byte[] salt = getSalt();
        String securePassword = get_SHA_256_SecurePassword(OriginPass, salt);

        return securePassword;
    }

    public String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt){

        //Use MessageDigest md = MessageDigest.getInstance("SHA-256");
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();

            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
