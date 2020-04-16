package com.hefeibo.springboot.controller;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * <h1>加密工具类</h1>
 *
 * @Date 2018年12月13日
 */
public class MD5 {
    /**
     * md5加密
     *
     * @param target 待加密字符串
     * @return 加密后的MD5字符串
     * @throws Exception 异常
     */
    public static String encryptMd5(String target) throws Exception {
        if (target == null || target.length() <= 0) {
            return null;
        }
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return byte2hex(md5.digest(target.getBytes("utf-8")));
    }
    /**
     * 利用java原生的摘要实现SHA256加密
     *
     * @param target 需要加密的字符串
     * @return String
     * @throws Exception 异常
     */
    public static String encryptSha256(String target) throws Exception {
        if (target == null || target.length() == 0) {
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(target.getBytes("UTF-8"));
        return byte2hex(messageDigest.digest());
    }
    /**
     * 加密
     *
     * @param target 加密对象
     * @param sKey 加密密钥
     * @return 加密后的字符串
     * @throws Exception 异常
     */
    public static String encryptAes(String target, String sKey) throws
            Exception {
        if (target == null || target.length() <= 0) {
            return null;
        }
// 判断Key是否正确
        if (sKey == null || sKey.length() == 0) {
            throw new IllegalArgumentException("illegal sKey");
        }
// 判断Key是否为16位
        if (sKey.length() != 16) {
            throw new IllegalArgumentException("illegal sKey");
        }
        byte[] raw = sKey.getBytes("UTF-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(target.getBytes());
        return byte2hex(encrypted);
    }
    /**
     * 生产Authorization
     *
     * @param appId 解密对象
     * @param appKey 加密密钥
     * @return 解密后的原数据
     * @throws Exception 异常
     *
     */
    public static String generateAuthorization(String appId, String appKey)
            throws Exception {
        if (appId == null || appId.length() == 0) {
            throw new IllegalArgumentException("illegal appId");
        }
        if (appKey == null || appKey.length() == 0) {
            throw new IllegalArgumentException("illegal appKey");
        }
// 生产AES密钥
        String aesKey = encryptMd5(appKey).substring(3, 19);
// 获取当前时间300秒后失效
        long currTime = System.currentTimeMillis();
// AES最终的加密
        return encryptAes(appId + currTime, aesKey);
    }
    // 二行制转字符串
    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }
    /**
     * @param args 参数
     * @throws Exception 异常
     */
    public static void main(String[] args) throws Exception {
// 校验值
// cc4ca20205d56ea2e408881b688da2bb
        System.out.println(encryptMd5("18812345678"));
// 校验值
// 93492748c362146f8e48dde778cdb703ead158e42de292307baf24a9b4d4e61b
        System.out.println(encryptSha256("18812345678"));
        String appId = "80be3886feec4e4cb6dc1bbba802f081";
        String appKey = "test123";
// 由于时间实时变化无校验值Authorization有效期时间为300秒
        System.out.println(generateAuthorization(appId, appKey));
    }
}

