package com.ghj.common.util;


import com.ghj.common.base.Constant;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author GeHejun
 * @date 2019-06-24
 */
public class DesEncryptDecrypt {
    private static DesEncryptDecrypt ourInstance = new DesEncryptDecrypt();

    public static DesEncryptDecrypt getInstance() {
        return ourInstance;
    }
    private Cipher ecipher,dcipher;


    private DesEncryptDecrypt(){
        DESKeySpec dks;
        try {
            dks = new DESKeySpec(Constant.ENCRYPT_DECRYPT_KEY.getBytes());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey desKey = skf.generateSecret(dks);
            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, desKey);
            dcipher.init(Cipher.DECRYPT_MODE, desKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

    }

    public String encrypt(String str)  {
        byte[] utf8 = new byte[0];
        try {
            utf8 = str.getBytes("UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] enc = new byte[0];
        try {
            enc = ecipher.doFinal(utf8);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new sun.misc.BASE64Encoder().encode(enc);
    }

    public String decrypt(String str) throws UnsupportedEncodingException {
        byte[] dec = new byte[0];
        try {
            dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] utf8 = new byte[0];
        try {
            utf8 = dcipher.doFinal(dec);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(utf8, "UTF8");
    }

}
