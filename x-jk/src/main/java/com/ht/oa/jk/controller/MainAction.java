package com.ht.oa.jk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.model.code.ResultCode;
import com.ht.oa.jk.model.resp.CommonResponseSimple;
import com.ht.oa.jk.utils.auth.LoginCheckUtils;
import com.ht.oa.jk.utils.cache.CacheMember;
import com.ht.oa.jk.utils.cache.RedisCacheFactory;
import com.ht.oa.jk.utils.common.ResultUtils;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.log.LogUtils;
import com.ht.oa.jk.utils.token.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainAction {

    /**
     * 登录
     */
    @RequestMapping("/login")
    @ResponseBody
    public Object login(HttpServletRequest request) {
        ServletInputStream in = null;
        try {
            if (LoginCheckUtils.isIntercept(request)) {
                return ResultUtils.paramNoPass("访问过于频繁");
            }
            in = request.getInputStream();
            StringBuilder requestMsg = new StringBuilder();
            byte[] b = new byte[4096];
            int l;
            while ((l = in.read(b)) != -1) {
                requestMsg.append(new String(b, 0, l, "UTF-8"));
            }
            if (StringUtils.isBlank(requestMsg.toString())) {
                return ResultUtils.paramNoPass("参数必传");
            }
            LogUtils.error(requestMsg.toString());
            JSONObject reqJson = JSON.parseObject(requestMsg.toString());
            String username = reqJson.getString("username");
            String password = reqJson.getString("password");
            if (StringUtils.isBlank(username)) {
                return ResultUtils.paramNoPass("用户名不能为空");
            }
            if (StringUtils.isBlank(password)) {
                return ResultUtils.paramNoPass("密码不能为空");
            }
            if ("13157184276".equals(username) && "cv#dc09".equals(password)) {
                String accessToken = TokenUtils.getAccessToken();
                Map<String, Object> result = new HashMap<>();
                result.put("code", ResultCode.success.code());
                result.put("accessToken", accessToken);
                CacheMember cacheMember = new CacheMember();
                cacheMember.setMid(0L);
                cacheMember.setMobile("13157184276");
                cacheMember.setNickName("glc");
                RedisCacheFactory.setString(accessToken, 86400, JSON.toJSONString(cacheMember));
                return result;
            } else {
                LoginCheckUtils.addLoginErrorCount(request);
                return ResultUtils.paramNoPass("用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("MainAction%login", e);
            return ResultUtils.exception();
        }
    }

}
