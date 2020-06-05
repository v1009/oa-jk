package com.ht.oa.jk.utils.auth;

import com.ht.oa.jk.utils.cache.RedisCacheFactory;
import com.ht.oa.jk.utils.common.BaseUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginCheckUtils {

    /**
     * 检测登录错误次数是否触发拦截 , 错误15次则拦截
     */
    public static boolean isIntercept(HttpServletRequest request) {
        String requestIp = BaseUtils.getRequestIp(request);
        String count = RedisCacheFactory.getString(requestIp);
        if (count == null) {
            return false;
        }
        if (Integer.parseInt(count) > 30) {
            return true;
        }
        return false;
    }

    /**
     * 新增登录错误次数
     */
    public static void addLoginErrorCount(HttpServletRequest request) {
        String requestIp = BaseUtils.getRequestIp(request);
        String count = RedisCacheFactory.getString(requestIp);
        //默认记录连续一天的错误次数
        if (count == null) {
            RedisCacheFactory.setString(requestIp, 86400, String.valueOf(1));
        } else {
            RedisCacheFactory.setString(requestIp, 86400, String.valueOf(Integer.parseInt(count + 1)));
        }
    }

}
