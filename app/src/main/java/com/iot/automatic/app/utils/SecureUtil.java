package com.iot.automatic.app.utils;

import com.alibaba.android.arouter.utils.TextUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

public final class SecureUtil {

    private static final String salt = "i0t@2OKL#&d";
    private static final String UTF_8 = "utf-8";

    private static final String SECURE_MD5 = "md5";
    public static final String SECURE_HMAC = "hmac";

    private SecureUtil() {
    }

    public static String md5(String str) {
        final String temp = str + salt.charAt(0) + salt.charAt(2) + salt.charAt(4) + salt.charAt(1);
        return str2Md5(temp);
    }

    private static String str2Md5(String pass) {
        if (TextUtils.isEmpty(pass)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(pass.getBytes(UTF_8));
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String createSign(Map<String, String> params, String accessToken) {
        try {
            return createSign(params, accessToken, SECURE_MD5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String createSign(Map<String, String> params, String accessToken, String signMethod) throws IOException {
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        StringBuilder query = new StringBuilder();
        if (SECURE_MD5.equals(signMethod)) {
            query.append(signMethod);
        }
        for (String key : keys) {
            String value = params.get(key);
            query.append(key).append(value);
        }
        byte[] bytes;
        if (SECURE_HMAC.equals(signMethod)) {
            bytes = encryptHMAC(query.toString(), accessToken);
        } else {
            query.append(accessToken);
            bytes = encryptMD5(query.toString());
        }
        return byte2hex(bytes);
    }

    private static byte[] encryptHMAC(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(UTF_8), "HmacMD5");
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(UTF_8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    private static byte[] encryptMD5(String data) throws IOException {
        return encryptMD5(data.getBytes(UTF_8));
    }

    private static byte[] encryptMD5(byte[] bytes) throws IOException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null == md ? null : md.digest(bytes);
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
}
