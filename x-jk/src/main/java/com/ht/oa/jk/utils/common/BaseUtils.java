package com.ht.oa.jk.utils.common;


import com.ht.oa.jk.config.ConfigParam;
import com.ht.oa.jk.utils.constant.AppConst;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-11-4
 * Time: 上午11:26
 * To change this template use File | Settings | File Templates.
 */
public class BaseUtils {

    public static boolean isEmpty(String input) {
        if (input == null || input.trim().equals("")) {
            return true;
        }
        return false;
    }


    /**
     * 获取服务器前缀路径
     */
    public static String getServerSuffixPath() {
        int isProxy = ConfigParam.isProxy;
        if (isProxy == 1) {//启动代理
            if (ConfigParam.proxyPort == 80 || ConfigParam.proxyPort == 443) {
                return ConfigParam.schema + "://" + ConfigParam.domain + "/";
            }
            return ConfigParam.schema + "://" + ConfigParam.domain + ":" + ConfigParam.proxyPort + "/";
        }
        return ConfigParam.schema + "://" + ConfigParam.domain + ":" + ConfigParam.port + "/";
    }

    /**
     * urlEncoder
     */
    public static String urlEncoder(String str) {
        try {
            str = URLEncoder.encode(str, AppConst.CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * urlDecoder
     */
    public static String urlDecoder(String str) {
        try {
            str = URLDecoder.decode(str, AppConst.CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取查询地址
     */
    public static String getFullUrl(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        if (request.getQueryString() != null) {
            url.append("?");
            url.append(request.getQueryString());
        }
        if ("https".equals(ConfigParam.schema)) {
            url = new StringBuffer(url.toString().replace("http://", "https://"));
        }
        return url.toString();
    }

    /**
     * 获取重定向的登录页面
     *
     * @param request
     * @return
     */
    public static String getRedirectLoginUrl(HttpServletRequest request) {
        return "redirect:" + getServerSuffixPath() + "login.htm";
    }

    /**
     * 获取uuid
     *
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取ip
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        //假如经过多层反向代理，则取第一个
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

}
