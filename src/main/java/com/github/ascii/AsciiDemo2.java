package com.github.ascii;

import java.util.Arrays;

public class AsciiDemo2 {
    public static void main(String[] args) {
        String str = "AaZ";
        char []chs = str.toCharArray();
        int []result = new int[chs.length];
        for(int i = 0; i < result.length; i ++) {
            result[i] = chs[i];
        }
        System.out.println(Arrays.toString(result));
    }
}
