package com.ht.oa.jk.utils.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.model.SMenu;
import com.ht.oa.jk.model.TreeMenu;
import com.ht.oa.jk.utils.common.ResultUtils;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.log.LogUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 获取请求的body参数
     *
     * @param request
     * @return
     */
    public static String getRequestBody(HttpServletRequest request) {
        ServletInputStream in = null;
        try {
            in = request.getInputStream();
            StringBuilder requestMsg = new StringBuilder();
            byte[] b = new byte[4096];
            int l;
            while ((l = in.read(b)) != -1) {
                requestMsg.append(new String(b, 0, l, "UTF-8"));
            }
            LogUtils.error("请求参数：" + requestMsg.toString());
            return requestMsg.toString();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("解析参数错误：", e);
        }
        return null;
    }

}
