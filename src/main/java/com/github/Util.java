package com.github;

import java.io.*;

public class Util {
    public static void print(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(byte aByte: bytes) {
            sb.append(aByte).append(" ");
        }
        System.out.println(sb);
    }

    public static String file2String(String path) throws IOException {
        FileReader reader = new FileReader(path);
        char[] buffer = new char[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while((len = reader.read(buffer)) != -1) {
            sb.append(buffer, 0, len);
        }
        return sb.toString();
    }

    public static void string2File(String data, String path) {
        FileWriter writer = null;
        try {
            File file = new File(path);
            if(!file.exists()) {
                file.createNewFile();
            }
            writer = new FileWriter(path);
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String inputStream2String(InputStream in) throws IOException {
        int len;
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while((len = in.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        baos.close();
        return baos.toString("UTF-8");
    }
}
