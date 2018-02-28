package com.tentcoo.gateway.util;

import java.util.Map;

/**
 * Created by rover on 2018/2/28.
 */
public class RSATester {

    static String publicKey;
    static String privateKey;

    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
            System.err.println("公钥: \n\r" + publicKey);
            System.err.println("私钥： \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        testSign();
        //testJavaRsa();
    }

    static void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String source = "你好啊，X老师！s1@324234234sfsfsf";
        System.out.println("原文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        System.out.println("加密后：\r\n" + new String(encodedData));//乱码
        String encodeDataBase64 = Base64Utils.encode(encodedData);
        System.out.println("----------------:base64处理：" + encodeDataBase64);
        byte[] decodedData = RSAUtils.decryptByPublicKey(Base64Utils.decode(encodeDataBase64), publicKey);//encodedData
        String target = new String(decodedData);
        System.out.println("解密后: " + target);
        /*System.err.println("私钥签名——公钥验证签名");
        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("签名:\r" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\r\n" + status);*/
    }

    static void testJavaRsa() {
        String data = "你好啊，X老师！s1@324234234sfsfsf";
        data = RSAUtils.encryptedDataOnJava(data, publicKey);
        System.out.println("加密数据：" + data);
        System.out.println("解密数据：" + RSAUtils.decryptDataOnJava(data, privateKey));
    }

}
