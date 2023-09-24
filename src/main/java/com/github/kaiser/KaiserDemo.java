package com.github.kaiser;

public class KaiserDemo {
    public static void main(String[] args) {
        // 定义原文
        String input = "Hello World";
        // 将原文右边移动三位
        int key = 3;
        // 将原文转为字符数组
        char[] chars = input.toCharArray();
        // 移动
        StringBuilder sb = new StringBuilder();
        for (char aChar: chars) {
            int b = aChar + key;
            char newChar = (char) b;
            sb.append(newChar);
        }
        System.out.println("密文:" + sb); // 密文: Khoor#Zruog
    }
}
