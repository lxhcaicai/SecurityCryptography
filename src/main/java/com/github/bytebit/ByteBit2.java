package com.github.bytebit;

import java.nio.charset.StandardCharsets;

public class ByteBit2 {
    public static void main(String[] args) {
        String a = "a";
        byte[] bytes = a.getBytes();
        for (byte b: bytes) {
            int c = b;
            System.out.println("c = " + c);
            String s = Integer.toBinaryString(c); // c = 97 ，发现 byte 实际上就是 ascii 码
            System.out.println("s = " + s); // s = 1100001
        }
    }
}
