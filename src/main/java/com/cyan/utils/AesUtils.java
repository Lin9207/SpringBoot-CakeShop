package com.cyan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AesUtils {

    private static Logger logger = LoggerFactory.getLogger(AesUtils.class);

    /**
     * 加密
     *
     * @param content - 需要加密的内容
     * @param key     - 加密秘钥
     * @return - 返回加密后的内容
     * @throws Exception
     */
    public static String Encrypt(String content, String key) throws Exception {

        if (key == null) {
            logger.error("Key为空null");
            return null;
        }
        if (key.length() != 16) {
            logger.error("Key长度不是16位");
            return null;
        }

        byte[] raw = key.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  //设置密钥规范为AES
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        //CBC模式需要配置偏移量，设置一个向量，达到密码唯一性，增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec("1234567890123456".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes());

        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码功能
    }

    /**
     * 解密方法
     *
     * @param content - 需要解密的密文
     * @param key     - 解密的秘钥
     * @return - 解密后的内容
     * @throws Exception
     */
    public static String Decrypt(String content, String key) throws Exception {
        try {
            if (key == null) {
                logger.error("Key为空null");
                return null;
            }
            if (key.length() != 16) {
                logger.error("Key长度不是16位");
                return null;
            }

            byte[] raw = key.getBytes(StandardCharsets.US_ASCII); //参数类型
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  //"算法/模式/补码方式"
            //CBC模式需要配置偏移量，设置这个后，不会出来同一个明文加密为同一个密文的问题，达到密文唯一性
            IvParameterSpec iv = new IvParameterSpec("1234567890123456".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(content);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
