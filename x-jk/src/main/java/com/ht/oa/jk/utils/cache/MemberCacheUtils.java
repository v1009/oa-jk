package com.ht.oa.jk.utils.cache;

import com.alibaba.fastjson.JSON;

public class MemberCacheUtils {

    /**
     * 登录成功，设置缓存
     */
    public static void login(String accessToken, CacheMember cacheMember) {
        RedisCacheFactory.setString(accessToken, 3600 * 24 * 30, JSON.toJSONString(cacheMember));
    }

    /**
     * 获取缓存用户
     */
    public static CacheMember getCacheMember(String accessToken) {
        String member = RedisCacheFactory.getString(accessToken);
        if (member == null) {
            return null;
        }
        return JSON.parseObject(member, CacheMember.class);
    }

    /**
     * 退出登录，清除缓存
     */
    public static void logout(String accessToken) {
        RedisCacheFactory.removeKey(accessToken);
    }

    /**
     * 判断是否登录
     *
     * @param sessionToken
     * @return
     */
    public static boolean isLogin(String sessionToken) {
        return RedisCacheFactory.existKey(sessionToken);
    }

}
