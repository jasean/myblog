package com.hbwh.xj.myblog.util.tool;

public class ByteUtils {
    private static final String HEX_NUMS_STR="0123456789ABCDEF";

    /**
     * 将字节数组转换为十六进制字符串
     * 原理如下：
     *              for (int i = 0; i < j; i++) {
     *                 byte byte0 = md[i];
     *                 str[k++] = hexDigits[byte0 >>> 4 & 0xf];
     *                 str[k++] = hexDigits[byte0 & 0xf];
     *             }
     * @param b
     * @return
     */
    public static String byte2HexStr(byte[] b){
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }

    /**
     * 将十六进制字符串转化为字节数组
     * @param hex
     * @return
     */
    public static byte[] hexStr2Bytes(String hex){
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4
                    | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }
}
