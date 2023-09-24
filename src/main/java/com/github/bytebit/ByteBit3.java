package com.github.bytebit;

import java.io.UnsupportedEncodingException;

public class ByteBit3 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String a = "许";
//        byte[] bytes = a.getBytes();
        byte[] bytes = a.getBytes("GBK");
        for(byte b: bytes) {
            int c = b;
            System.out.println("c = " + c); // c = 97 ，发现 byte 实际上就是 ascii 码
            String s = Integer.toBinaryString(c);
            System.out.println("s = " + s);
        }
    }
}
