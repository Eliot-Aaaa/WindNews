package com.eliot.news.mynews.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ProjectName: WindNews
 * @Package: com.eliot.news.mynews.util
 * @ClassName: Md5Helper
 * @Description: 类描述
 * @Author: 作者
 * @CreateDate: 2020/7/9 10:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/9 10:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Md5Helper {
    private static MessageDigest mDigest = null;

    static {
        try {
            mDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String toMD5(String key)
    {
        String cacheKey;
        if (mDigest == null)
            return String.valueOf(key.hashCode());
        mDigest.update(key.getBytes());
        cacheKey = byteToHexString(mDigest.digest());
        return cacheKey;
    }

    public static String byteToHexString(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++)
        {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1)
                sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }

    public static String MD5(String md5)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++)
            {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
