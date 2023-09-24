package com.github.des;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DesDemo2 {
    public static void main(String[] args) throws Exception {
        // 原文
        String input = "喵呜";
        // 定义 key，如果使用 DES 进行加密，那么密钥 key 必须是 8 个字节
        String key = "12345678";
        // 算法，表示采用什么加密
        String transformation = "DES";
        String algorithm = "DES"; // 加密类型
        // 加密：获取密文
        String encrypt = encrypt(input, key, transformation, algorithm);
        System.out.println("加密 = " + encrypt);
        // 解密：获取原文
        String decrypt = decrypt(encrypt, key, transformation, algorithm);
        System.out.println("解密 = " + decrypt);
    }

    /**
     * 使用 DES 进行加密
     *
     * @param input
     * @param key
     * @param transformation
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static String encrypt(String input, String key, String transformation, String algorithm) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 进行加密初始化
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        // 第一个参数是模式：分为加密模式 Cipher.ENCRYPT_MODE 和解密模式 Cipher.DECRYPT_MODE
        // 第二个参数是加密的规则
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 进行加密，参数是原文的字节数组
        byte[] bytes = cipher.doFinal(input.getBytes());
        // 对数据进行 base64 编码
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(bytes);
        return new String(encode);
    }

    public static String decrypt(String input, String key, String transformation, String algorithm) throws Exception {
        // 创建解密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 进行解密初始化
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm); // 指定加密的规则，其中第一个参数是密钥，第二个参数表示加密类型
        // 第一个参数是模式：分为加密模式 Cipher.ENCRYPT_MODE 和解密模式 Cipher.DECRYPT_MODE
        // 第二个参数是解密的规则
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        // 进行解密
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(input.getBytes()));
        return new String(bytes);
    }
}
