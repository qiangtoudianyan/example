package com.zzl.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author: 张志龙
 * @Date: 2018/9/10 15:04
 * @Description:
 */
public class EncryptUtil {
    public static String encrypt(String str, String key, String charset) {
        try {
            if (str == null || str.length() < 1) {
                return "";
            }
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(keySpec);
            Cipher c1 = Cipher.getInstance("DES");
            c1.init(1, secretKey);
            byte[] cipherByte = c1.doFinal(str.getBytes());
            String result = bytes2Hex(cipherByte);
            if ((charset != null) && (charset.length() >= 1)) {
                result = URLEncoder.encode(result, charset);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decrypt(String str, String key, String charset) {
        try {
            if (str == null || str.length() < 1) {
                return "";
            }
            DESKeySpec keySpec = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(keySpec);
            Cipher c1 = Cipher.getInstance("DES");
            c1.init(2, secretKey);
            String result = new String(c1.doFinal(hex2byte(str)));
            if (charset != null && charset.length() >= 1) {
                result = URLDecoder.decode(result, charset);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; ++i) {
            tmp = Integer.toHexString(bts[i] & 0xFF);
            if (tmp.length() == 1) {
                des = des + "0";
            }
            des = des + tmp;
        }
        return des;
    }

    private static byte[] hex2byte(String hexStr) {
        try {
            byte[] bts = new byte[hexStr.length() / 2];
            int i = 0;
            for (int j = 0; j < bts.length; ++j) {
                bts[j] = (byte) Integer.parseInt(hexStr.substring(i, i + 2), 16);
                i += 2;
            }
            return bts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "".getBytes();
    }

}
