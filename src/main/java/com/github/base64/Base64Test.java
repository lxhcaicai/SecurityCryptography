package com.github.base64;

import java.util.Base64;

public class Base64Test {
    public static void main(String[] args) {
        // MQ== 表示 1 个字节，不够三个字节，所以需要后面通过 == 号补齐
        System.out.println(new String(Base64.getEncoder().encode("1".getBytes())));
        // MTI= 表示 2 个字节，不够三个字节，所以需要后面通过 == 号补齐
        System.out.println(new String(Base64.getEncoder().encode("12".getBytes())));
        // MTIz 表示 3 个字节，够三个字节，所以需要不后面通过 == 号补齐
        System.out.println(new String(Base64.getEncoder().encode("123".getBytes())));
        // 大仙 是中文，占 6 个字节，6 * 8 = 48 ，刚好被整数，所以没有等号 5aSn5LuZ
        System.out.println(new String(Base64.getEncoder().encode("大仙".getBytes())));
    }
}
