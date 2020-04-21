package com.ht.oa.jk.utils.system;

import javax.servlet.http.HttpServletRequest;

public class SystemUtils {

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
     * 获取当前登录的mid
     *
     * @param request
     * @return
     */
    public static long getMid(HttpServletRequest request) {
        String mid = request.getParameter("mid");
        if (mid == null) {
            return 0L;
        }
        return Long.parseLong(mid);
    }

}
