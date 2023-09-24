package com.github.rsa;

import cn.hutool.core.io.FileUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSADemo3 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // 原文
        String input = "lxhcaicai";
        // 加密算法
        String algorithm = "RSA";
        // 保存密钥到本地文件中
        generateKeyToFile(algorithm, "id_rsa.pub", "id_rsa");
        // 读取私钥
        PrivateKey privateKey = getPrivateKey(algorithm, "id_rsa");
        // 读取公钥
        PublicKey publicKey = getPublicKey(algorithm,"id_rsa.pub");
        // 加密数据
        String encrypt = encrypt(algorithm, privateKey, input);
        // 解密数据
        String decrypt = decrypt(algorithm, publicKey, encrypt);
        System.out.println("decrypt = " + decrypt);
    }

    private static void generateKeyToFile(String algorithm, String pubPath, String priPath) throws NoSuchAlgorithmException {
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
        // 对公钥金额私钥 进行 Base64 编码
        String privateKeyStr = new String(Base64.getEncoder().encode(privateKeyEncoded));
        String publicKeyStr = new String(Base64.getEncoder().encode(publicKeyEncoded));
        // 保存密钥
        FileUtil.writeString(privateKeyStr, new File(priPath), StandardCharsets.UTF_8);
        FileUtil.writeString(publicKeyStr, new File(pubPath), StandardCharsets.UTF_8);

    }

    /**
     * 加密数据
     *
     * @param algorithm 算法
     * @param key       密钥
     * @param input     原文
     * @return
     */
    public static String encrypt(String algorithm, Key key, String input) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 加密
        return new String(Base64.getEncoder().encode(cipher.doFinal(input.getBytes(StandardCharsets.UTF_8))));
    }

    public static String decrypt(String algorithm, Key key, String encrypted) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 初始化
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 解密
        return new String(cipher.doFinal(Base64.getDecoder().decode(encrypted)));
    }

    public static PrivateKey getPrivateKey(String algorithm, String priPath) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 将文件内容转换为字符串
        String privateKeyStr = FileUtil.readString(new File(priPath), StandardCharsets.UTF_8);
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行 Base64 解码
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
        // 生成私钥
        return keyFactory.generatePrivate(spec);
    }

    public static PublicKey getPublicKey(String algorithm, String pubPath) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // 将文件内容转换为字符串
        String privateKeyStr = FileUtil.readString(new File(pubPath), StandardCharsets.UTF_8);
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 构建密钥规范 进行 Base64 解码
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
        // 生成私钥
        return keyFactory.generatePublic(spec);
    }
}
