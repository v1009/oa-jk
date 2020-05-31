package com.ht.oa.jk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.config.ApiDesc;
import com.ht.oa.jk.utils.auth.LoginCheckUtils;
import com.ht.oa.jk.utils.cache.CacheMember;
import com.ht.oa.jk.utils.cache.MemberCacheUtils;
import com.ht.oa.jk.utils.code.ResultCode;
import com.ht.oa.jk.utils.common.ResultUtils;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.log.LogUtils;
import com.ht.oa.jk.utils.system.RequestUtils;
import com.ht.oa.jk.utils.token.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainAction {

    @ApiDesc(name = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request) {
        try {
            if (LoginCheckUtils.isIntercept(request)) {
                return ResultUtils.param("访问过于频繁");
            }
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            String username = reqJson.getString("username");
            String password = reqJson.getString("password");
            if (StringUtils.isBlank(username)) {
                return ResultUtils.param("用户名不能为空");
            }
            if (StringUtils.isBlank(password)) {
                return ResultUtils.param("密码不能为空");
            }
            if ("13157184276".equals(username) && "cvc#dc09".equals(password)) {
                String accessToken = TokenUtils.getAccessToken();
                Map<String, Object> result = new HashMap<>();
                result.put("code", ResultCode.success.code());
                result.put("accessToken", accessToken);
                CacheMember cacheMember = new CacheMember();
                cacheMember.setMid(0L);
                cacheMember.setMobile("13157184276");
                cacheMember.setNickName("glc");
                MemberCacheUtils.login(accessToken, cacheMember);
                return result;
            } else {
                LoginCheckUtils.addLoginErrorCount(request);
                return ResultUtils.param("用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("MainAction%login", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "退出系统")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Object logout(HttpServletRequest request) {
        try {
            String accessToken = RequestUtils.getSessionToken(request);
            if (StringUtils.isBlank(accessToken)) {
                return ResultUtils.param("Token expired");
            }
            MemberCacheUtils.logout(accessToken);
            return ResultUtils.success("已退出");
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("MainAction%logout", e);
            return ResultUtils.exception();
        }
    }

}
