package com.ht.oa.jk.utils.auth;


import com.ht.oa.jk.utils.common.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class AuthTools {

    /**
     * 获取token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Token");
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return token;
    }


}
