package com.ht.oa.jk.utils.token;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class TokenUtils {

    public static final String charCom = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
    public static final int charLen = 63;
    public static final int expiresIn = 7200;

    /**
     * 生成accessToken
     */
    public static String getAccessToken() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            int random = new Random().nextInt(charLen);
            builder.append(charCom.substring(random, random + 1));
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        builder.append(uuid);
        return builder.toString();
    }

    /**
     * 获取过期时间
     */
    public static long getAccessTokenExpireTime() {
        return new Date().getTime() + expiresIn * 1000l;
    }

    /**
     * 判断时间是否过期
     */
    public static boolean isExpire(long expireTime) {
        long now = new Date().getTime();
        return now > expireTime;
    }

}
