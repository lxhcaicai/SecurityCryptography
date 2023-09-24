package com.github.rsa;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class RSADemo2 {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 加密算法
        String algorithm = "RSA";
        // 创建密钥对生成器对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥的字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 获取公钥的字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        String privateKeyStr = new String(Base64.getEncoder().encode(privateKeyEncoded));
        String publicKeyStr = new String(Base64.getEncoder().encode(publicKeyEncoded));

        // 私钥
        System.out.println("privateKeyStr = " + privateKeyStr);

        // 公钥
        System.out.println("publicKeyStr = " + publicKeyStr);

        // 原文
        String input = "lxhcaicai";
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        // 加密
        byte[] bytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
        // 私钥加密过后的数据
        System.out.println("私钥加密过后的数据 = " + new String(Base64.getEncoder().encode(bytes)));

        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // 解密
        String result = new String(cipher.doFinal(bytes));
        System.out.println("result = " + result);
    }
}
