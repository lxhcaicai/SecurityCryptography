package com.github.kaiser;

public class KaiserDemo2 {
    public static void main(String[] args) {
        // 定义原文
        String input = "Hello World";
        // 将原文右边移动三位
        int key = 3;

        // 调用加密方法
        String result = encrypt(input, key);
        System.out.println("密文：" + result); // 密文：Khoor#Zruog

        // 调用解密的方法
        result = decrtpt(result, key);
        System.out.println("原文：" + result); // 原文：Hello World
    }

    /**
     * 凯撒加密
     * @param original 原文
     * @param key 密钥
     * @return 加密后的时候，即密文
     */
    public static String encrypt(String original, int key) {
        // 将原文转为字符数组
        char []chars = original.toCharArray();
        // 移动
        StringBuilder sb = new StringBuilder();
        for(char aChar: chars) {
            int b = aChar + key;
            char newChar = (char) b;
            sb.append(newChar);
        }
        return sb.toString();
    }

    /**
     * 凯撒加密
     * @param encryptedData 密文
     * @param key 密钥
     * @return 解密的时候，即原文
     */
    public static String decrtpt(String encryptedData, int key) {
        char []chars = encryptedData.toCharArray();
        // 移动
        StringBuilder sb = new StringBuilder();
        for(char aChar: chars) {
            int b = aChar - key;
            char newChar = (char) b;
            sb.append(newChar);
        }
        return sb.toString();
    }
}
