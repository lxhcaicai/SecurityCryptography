package com.github.digest;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestDemo2 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 原文
        String input = "lxhcaicai";
        // 算法
        String algorithm = "MD5";
        String md5 = getDigest(input, algorithm);
        System.out.println("md5 = " + md5);

        algorithm = "SHA-1";
        String sha1 = getDigest(input, algorithm);
        System.out.println("sha1 = " + sha1);

        algorithm = "SHA-256";
        String sha256 = getDigest(input, algorithm);
        System.out.println("sha256 = " + sha256);

        algorithm = "SHA-512";
        String sha512 = getDigest(input, algorithm);
        System.out.println("sha512 = " + sha512); //
    }

    private static String toHex(byte[] digest) {
        // 将消息摘要转化成 16 进制
        StringBuilder sb = new StringBuilder();
        for (byte b: digest) {
            // 转换为 16 进制
            String s = Integer.toHexString(b & 0xff);
            // 如果 字符串长度为 0, 前面补 0
            if(s.length() == 0) {
                s = "0" + s;
            }
            sb.append(s);
        }
        return sb.toString();
    }

    private static String getDigest(String input, String algorithm) throws NoSuchAlgorithmException {
        // 获取消息摘要对象
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        // 获取消息数字摘要的字节数组
        byte[] digest = messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
        return toHex(digest);
    }
}
