package com.ht.oa.jk.utils.sso;

import com.ht.oa.jk.utils.cache.CacheMember;

public class ApiUtils {

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
        return 0L;
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
