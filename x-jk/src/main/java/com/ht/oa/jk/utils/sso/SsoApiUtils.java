package com.ht.oa.jk.utils.sso;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.config.ConfigParam;
import com.ht.oa.jk.utils.cache.CacheMember;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.http.HttpClientUtil;

public class SsoApiUtils {

    private static String accessToken = null;

    /**
     * 获取访问token
     */
    private static void getToken() {
    }

    /**
     * 获取系统成员
     *
     * @param token
     * @return
     */
    public static String getSystemPerson(String token) {
        return null;
    }

    /**
     * 查询系统归属人
     *
     * @param token
     * @return
     */
    public static long getSystemOwnerId(String token) {
        return 1L;
    }

    /**
     * 通过 cookie 里面的token【isg】获取用户信息
     *
     * @return
     */
    public static CacheMember getCacheMember(String token) {
        return null;
    }

}
