package com.hbwh.xj.myblog.util.tool;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class MD5Utils {

    private static final int SALT_LENGTH = 16;

    static MessageDigest mdInst = null;

    static {
        try {
            mdInst = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 不加盐md5
     * @param key
     * @return
     */
    public static String md5(String key){
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
//            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            return ByteUtils.byte2HexStr(md);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 加盐md5
     * 原理：将随机生成的盐值拼接在加盐md5值的前面保存在数据库中，
     *      验证时可以根据数据库中的值取出salt，然后对密码做校验；
     * @param key
     * @return
     */
    public static String md5BySalt(String key)  {
        SecureRandom random = new SecureRandom();
        //声明盐数组变量
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        //将盐数据传入消息摘要对象
        mdInst.update(salt);
        try {
            mdInst.update(key.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        byte[] digest = mdInst.digest();

        //因为要在口令的字节数组中存放盐，所以加上盐的字节长度
        byte[] pwd = new byte[digest.length + SALT_LENGTH];
        //将盐的字节拷贝到生成的加密口令字节数组的前12个字节，以便在验证口令时取出盐
        System.arraycopy(salt, 0, pwd, 0, SALT_LENGTH);
        //将消息摘要拷贝到加密口令字节数组从第13个字节开始的字节
        System.arraycopy(digest, 0, pwd, SALT_LENGTH, digest.length);

        return ByteUtils.byte2HexStr(pwd);
    }

    /**
     * 校验给定原数据与md5值是否匹配
     * @param raw 源数据
     * @param md5Value
     * @return
     */
    public static boolean verifyMD5(String raw, String md5Value) {
        //将16进制字符串格式口令转换成字节数组
        byte[] pwdInDb = ByteUtils.hexStr2Bytes(md5Value);
        //声明盐变量
        byte[] salt = new byte[SALT_LENGTH];
        System.arraycopy(pwdInDb, 0, salt, 0, SALT_LENGTH);
        mdInst.update(salt);
        try {
            mdInst.update(raw.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //生成输入口令的消息摘要
        byte[] digest = mdInst.digest();
        //声明一个保存数据库中口令消息摘要的变量
        byte[] digestInDb = new byte[pwdInDb.length - SALT_LENGTH];
        //取得数据库中口令的消息摘要
        System.arraycopy(pwdInDb, SALT_LENGTH, digestInDb, 0, digestInDb.length);
        //比较根据输入口令生成的消息摘要和数据库中消息摘要是否相同
        if (Arrays.equals(digest, digestInDb)) {
            //口令正确返回口令匹配消息
            return true;
        } else {
            //口令不正确返回口令不匹配消息
            return false;
        }
    }

    public static void main(String[] args){
        String s = "abcdefg";
        System.out.println(md5(s));
    }
}
