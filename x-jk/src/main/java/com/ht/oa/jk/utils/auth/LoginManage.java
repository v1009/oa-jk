package com.ht.oa.jk.utils.auth;

import com.ht.oa.jk.utils.cache.CacheMember;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.sso.SsoApiUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-11-13
 * Time: 下午4:31
 * To change this template use File | Settings | File Templates.
 */
public class LoginManage {

    public static CacheMember getCacheUser(HttpServletRequest request, HttpServletResponse response) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals("isg")) {
                    token = cookie.getValue();
                }
            }
        }
        if (!StringUtils.isBlank(token)) {
            return SsoApiUtils.getCacheMember(token);
        }
        return null;
    }

}
