package com.ht.oa.jk.utils.auth;

import com.alibaba.fastjson.JSON;
import com.ht.oa.jk.model.resp.CommonResponseLogin;
import com.ht.oa.jk.model.resp.CommonResponseSimple;
import com.ht.oa.jk.utils.cache.MemberCacheUtils;
import com.ht.oa.jk.utils.common.ResultUtils;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.system.RequestUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AuthFilter implements Filter {

    private static List<String> noFilterUrls = new ArrayList<>();

    static {
        noFilterUrls.add("/login");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String reqUrl = request.getRequestURI();
        //非过滤的url
        for (String url : noFilterUrls) {
            if (reqUrl.startsWith(url)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        //获取系统token
        String token = RequestUtils.getRequestToken(request);
        if (StringUtils.isBlank(token)) {
            response.setContentType("application/json;charset=utf-8");
            CommonResponseSimple commonResponse = ResultUtils.param("token required");
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(commonResponse));
            out.flush();
            out.close();
            return;
        }
        boolean isAssign = RequestUtils.isTokenAssignSystem(request);
        if (!isAssign) {
            response.setContentType("application/json;charset=utf-8");
            CommonResponseSimple commonResponse = ResultUtils.busiFail("token not assign system");
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(commonResponse));
            out.flush();
            out.close();
            return;
        }
        //判断会话访问token
        String sessionToken = RequestUtils.getCookieToken(request);
        if (StringUtils.isBlank(sessionToken)) {
            response.setContentType("application/json;charset=utf-8");
            CommonResponseSimple commonResponse = ResultUtils.param("Token required");
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(commonResponse));
            out.flush();
            out.close();
            return;
        }
        boolean isLogin = MemberCacheUtils.isLogin(sessionToken);
        if (!isLogin) {
            response.setContentType("application/json;charset=utf-8");
            CommonResponseLogin commonResponse = ResultUtils.login();
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(commonResponse));
            out.flush();
            out.close();
            return;
        }
        filterChain.doFilter(request, response);
    }

}
