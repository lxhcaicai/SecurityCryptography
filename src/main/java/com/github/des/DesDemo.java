package com.github.des;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 对称加密
 */
public class DesDemo {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // 原文
        String input = "喵呜";
        // 定义 key，如果使用 DES 进行加密，那么密钥 key 必须是 8 个字节
        String key = "12345678";
        // 算法，表示采用什么加密
        String transformation = "DES";
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 进行加密初始化
        String algorithm = "DES";
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm); // 指定
        // 第一个参数是模式：分为加密模式 Cipher.ENCRYPT_MODE 和解密模式 Cipher.DECRYPT_MODE
        // 第二个参数是加密的规则
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 进行加密，参数是原文的字节数组
        byte[] bytes = cipher.doFinal(input.getBytes());
        // 打印字节，因为 ascii 码有负数，解析不出来，所以乱码
        for (byte aByte: bytes) {
            System.out.println(aByte);
        }
        // 打印密文，会出现乱码，因为在编码表上面找不到对应的字符
        System.out.println(new String(bytes));
        // 对数据进行 base64编码
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(bytes);
        System.out.println(new String(encode));
    }
}
