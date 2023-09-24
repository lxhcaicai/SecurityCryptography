package com.github.digest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DigestDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 原文
        String input = "许大仙";
        // 算法
        String algorithm = "MD5";
        // 获取消息信息摘要对象
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 获取消息数字摘要的字节数组
        byte[] digest = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        // 输出，可能会有乱码
        System.out.println(new String(digest));
        // 使用Base64 编码进行输出
        System.out.printf(new String(Base64.getEncoder().encode(digest)));
    }
}
