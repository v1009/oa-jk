package com.ht.oa.jk.utils.system;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    /**
     * 获取系统token
     *
     * @param request
     * @return
     */
    public static String getRequestToken(HttpServletRequest request) {
        return request.getParameter("token");
    }

    /**
     * 获取会话Token
     *
     * @param request
     * @return
     */
    public static String getSessionToken(HttpServletRequest request) {
        return request.getHeader("Token");
    }

    /**
     * 判断系统和token是否对应
     *
     * @param request
     * @return
     */
    public static boolean isTokenAssignSystem(HttpServletRequest request) {
        return true;
    }

    /**
     * 查询系统归属人
     *
     * @param request
     * @return
     */
    public static long getSystemOwnerId(HttpServletRequest request) {
        String token = RequestUtils.getRequestToken(request);
        return 1L;
    }

}
