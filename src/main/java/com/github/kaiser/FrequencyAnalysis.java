package com.github.kaiser;

import com.github.Util;

import java.io.IOException;
import java.util.*;

public class FrequencyAnalysis {
    // 英文里出现次数最多的字符
    private static final char MAGIC_CHAR = 'e';
    // 破解生成的最大文件数
    private static final int DE_MAX_FILE = 4;

    public static void main(String[] args) throws IOException {
        // 测试1，统计字符个数
        printCharCount(FrequencyAnalysis.class.getResource("/article.txt").getPath());

        // 加密文件
        int key = 3;
        encryptFile(FrequencyAnalysis.class.getResource("/article.txt").getPath(),"/article_en.txt", key);

        // 读取加密后的文件
        String article = Util.file2String("/article_en.txt");
        // 解密（会生成多个备选文件）
        decryptCaesarCode(article, "article_de.txt");
    }

    private static void printCharCount(String path) throws IOException {
        String data = Util.file2String(path);
        List<Map.Entry<Character, Integer>> mapList = getMaxCountChar(data);
        for(Map.Entry<Character, Integer> entry: mapList) {
            // 输出前几位的统计信息
            System.out.println("字符'" + entry.getKey() + "'出现" + entry.getValue() + "次");
        }
    }

    public static void encryptFile(String srcFile, String destFile, int key) throws IOException {
        String article = Util.file2String(srcFile);
        // 加密文件
        String encryptData = KaiserDemo2.encrypt(article, key);
        // 保存加密后的文件
        Util.string2File(encryptData, destFile);
    }

    public static void decryptCaesarCode(String input, String destPath) {
        int deCount = 0; // 当前解密生成的备选文件数
        // 获取出现频率最高的字符信息（出现次数越多越靠前）
        List<Map.Entry<Character,Integer>> mapList = getMaxCountChar(input);
        for(Map.Entry<Character, Integer> entry: mapList) {
            // 限制解密文件备选数
            if (deCount >= DE_MAX_FILE) {
                break;
            }
            // 输出前几位的统计信息
            System.out.println("字符'" + entry.getKey() + "'出现" + entry.getValue() + "次");

            ++deCount;
            // 出现次数最高的字符跟 MAGIC_CHAR 的偏移量即为秘钥
            int key = entry.getKey() - MAGIC_CHAR;
            System.out.println("猜测key = " + key + "， 解密生成第" + deCount + "个备选文件" + "\n");
            String decrypt = KaiserDemo2.decrtpt(input, key);

            String fileName = "de_" + deCount + destPath;
            Util.string2File(decrypt, fileName);
        }
    }

    /**
     * 统计String里出现最多的字符
     * @param data
     * @return
     */
    public static List<Map.Entry<Character,Integer>> getMaxCountChar(String data) {
        Map<Character, Integer> map = new HashMap<>();
        char[] array = data.toCharArray();
        for(char c: array) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                Integer count = map.get(c);
                map.put(c, count + 1);
            }
        }

        // 获取获取最大值
        int maxCount = 0;;
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            // 不统计空格
            if(entry.getValue() > maxCount) {
                maxCount = entry.getValue();
            }
        }
        // map 转换成 list 便于排序
        List<Map.Entry<Character, Integer>> mapList = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
        // 根据字符出现次数排序
        Collections.sort(mapList, (o1,o2)->o2.getValue().compareTo(o1.getValue()));
        return mapList;
    }
}
