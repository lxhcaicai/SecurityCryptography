package com.github.signature;

import com.github.rsa.RSAUtil;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class SignatureDemo {
    public static void main(String[] args) throws Exception {
        // 原文
        String input = "lxhcaicai";
        // 加密算法
        String algorithm = "RSA";
        // 保存密钥到本地文件中
        RSAUtil.generateKeyToFile(algorithm, "id_rsa.pub", "id_rsa");
        // 读取私钥
        PrivateKey privateKey = RSAUtil.getPrivateKey(algorithm, "id_rsa");
        // 读取公钥
        PublicKey publicKey = RSAUtil.getPublicKey(algorithm,"id_rsa.pub");
        // 获取数字签名
        String signatureData = getSignature(input, "SHA256withRSA", privateKey);
        System.out.println("signatureData = " + signatureData);

        // 校验数字签名
        boolean b = verifySignature(input, "SHA256withRSA", publicKey, signatureData);
        System.out.println("b = " + b);
    }

    /**
     * 生成签名
     *
     * @param input      原文
     * @param algorithm  算法
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String getSignature(String input, String algorithm, PrivateKey privateKey) throws Exception{
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initSign(privateKey);
        // 传入原文
        signature.update(input.getBytes(StandardCharsets.UTF_8));
        // 开启签名
        byte[] sign = signature.sign();
        return Base64.getEncoder().encodeToString(sign);
    }


    /**
     * 校验签名
     *
     * @param input         原文
     * @param algorithm     算法
     * @param publicKey     公钥
     * @param signatureData 签名
     * @return
     * @throws Exception
     */
    public static boolean verifySignature(String input, String algorithm, PublicKey publicKey, String signatureData) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(input.getBytes());
        // 校验数据
        return signature.verify(Base64.getDecoder().decode(signatureData.getBytes()));
    }
}
