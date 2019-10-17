package com.lsw.carmanager.algorithm;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Created by sweeneyliu on 2019/7/31.
 */
public class Idea {
    public static final String ALGORITHM = "IDEA";
    public static final String CIPHER_ALGORITHM = "IDEA/ECB/ISO10126Padding";

    // 获取 DES Key
    public static byte[] getDesKey() {
        try {
            Security.addProvider(new BouncyCastleProvider());
            // 1、创建密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM, "BC");
            keyGenerator.init(128);
            // 2、产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 3、获取密钥
            byte[] key = secretKey.getEncoded();
            return key;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // DES 解密
    public static byte[] encryptIdea(byte[] data, byte[] key) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
            // 加工作模式和填充方式
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] rsData = cipher.doFinal(data);
            return rsData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decryptIdea(byte[] data, byte[] key) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
            // 加工作模式和填充方式
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] rsData = cipher.doFinal(data);
            return rsData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
