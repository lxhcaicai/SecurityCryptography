package com.github.bytebit;

public class ByteBit {
    public static void main(String[] args) {
        String a = "a";
        byte[] bytes = a.getBytes();
        for (byte b : bytes) {
            int c = b;
            System.out.println("c = " + c); // c = 97 ，发现 byte 实际上就是 ascii 码
        }
    }
}
