package com.ht.oa.jk.utils.cache;

import com.alibaba.fastjson.JSON;

public class MemberCacheUtils {

    /**
     * 添加用户到缓存
     */
    public static void setCacheMember(String accessToken, CacheMember cacheMember) {
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


}
