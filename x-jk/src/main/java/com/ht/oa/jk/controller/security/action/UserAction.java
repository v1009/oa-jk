package com.ht.oa.jk.controller.security.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.config.ApiDesc;
import com.ht.oa.jk.controller.security.service.UserService;
import com.ht.oa.jk.model.SUserRole;
import com.ht.oa.jk.model.SUsers;
import com.ht.oa.jk.model.req.SUsersReq;
import com.ht.oa.jk.utils.cache.CacheMember;
import com.ht.oa.jk.utils.cache.MemberCacheUtils;
import com.ht.oa.jk.utils.common.DateUtils;
import com.ht.oa.jk.utils.common.ResultUtils;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.crypto.PwdUtils;
import com.ht.oa.jk.utils.log.LogUtils;
import com.ht.oa.jk.utils.seq.IdWorker;
import com.ht.oa.jk.utils.system.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserAction {

    @Autowired
    private UserService userService;

    @ApiDesc(name = "添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            String mobile = reqJson.getString("mobile");
            String pwd = reqJson.getString("pwd");
            String userName = reqJson.getString("userName");
            String email = reqJson.getString("email");
            String address = reqJson.getString("address");
            int salt = (int) (Math.random() * 10000) + 1;
            String secretUserPwd = PwdUtils.getSecretPwd(pwd, salt);
            Date now = DateUtils.getNowDate();
            SUsers sUsers = new SUsers();
            sUsers.setUserId(IdWorker.nextId());
            sUsers.setUserPhone(mobile);
            sUsers.setUserName(userName);
            sUsers.setUserEmail(email);
            sUsers.setAddress(address);
            sUsers.setUserPwd(secretUserPwd);
            sUsers.setSalt(String.valueOf(salt));
            sUsers.setRegisterTime(now);
            sUsers.setLastTime(now);
            sUsers.setStatus(1);
            boolean boo = userService.add(sUsers);
            if (boo) {
                return ResultUtils.success("添加成功");
            } else {
                return ResultUtils.busiFail("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("UserAction%add", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "查询用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            Integer page = reqJson.getInteger("page");
            if (page == null) {
                page = 1;
            }
            SUsersReq sUsersReq = new SUsersReq();
            sUsersReq.setPage(page);
            List<Map<String, Object>> list = userService.list(sUsersReq);
            return ResultUtils.list(list);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("UserAction%list", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "修改用户信息")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Object modify(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            long userId = reqJson.getLongValue("userId");
            String userName = reqJson.getString("userName");
            String email = reqJson.getString("email");
            String address = reqJson.getString("address");
            Date now = DateUtils.getNowDate();
            SUsers sUsers = new SUsers();
            sUsers.setUserId(userId);
            sUsers.setUserName(userName);
            sUsers.setUserEmail(email);
            sUsers.setAddress(address);
            sUsers.setLastTime(now);
            sUsers.setStatus(1);
            boolean boo = userService.modify(sUsers);
            if (boo) {
                return ResultUtils.success("修改成功");
            } else {
                return ResultUtils.busiFail("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("UserAction%modify", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "删除用户")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            long userId = reqJson.getLongValue("userId");
            Date now = DateUtils.getNowDate();
            SUsers sUsers = new SUsers();
            sUsers.setUserId(userId);
            sUsers.setLastTime(now);
            sUsers.setStatus(1);
            boolean boo = userService.del(sUsers);
            if (boo) {
                return ResultUtils.success("删除成功");
            } else {
                return ResultUtils.busiFail("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("UserAction%del", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "查询用户信息")
    @RequestMapping(value = "/findModelByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Object findModelByUserId(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            String accessToken = RequestUtils.getSessionToken(request);
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(accessToken);
            if (cacheMember == null) {
                return ResultUtils.login();
            }
            long userId = reqJson.getLongValue("userId");
            Map<String, Object> user = userService.queryModelByUserId(userId);
            return ResultUtils.item(user);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("UserAction%findModelByUserId", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "授权角色给用户")
    @RequestMapping(value = "/addRoleToUser", method = RequestMethod.POST)
    @ResponseBody
    public Object addRoleToUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            String accessToken = RequestUtils.getSessionToken(request);
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(accessToken);
            if (cacheMember == null) {
                return ResultUtils.login();
            }
            long ownerMid = RequestUtils.getSystemOwnerId(request);
            long userId = reqJson.getLongValue("userId");
            String rIds = reqJson.getString("rIds");
            String[] rIdArr = rIds.split(",");
            long[] rIdList = new long[rIdArr.length];
            for (int i = 0; i < rIdArr.length; i++) {
                rIdList[i] = Long.parseLong(rIdArr[i]);
            }
            List<SUserRole> sUserRoleList = new ArrayList<>();
            Date now = DateUtils.getNowDate();
            for (long roleId : rIdList) {
                SUserRole sUserRole = new SUserRole();
                sUserRole.setId(IdWorker.nextId());
                sUserRole.setUserId(userId);
                sUserRole.setRoleId(roleId);
                sUserRole.setInsertTime(now);
                sUserRole.setAddMid(userId);
                sUserRole.setOwnerMid(ownerMid);
                sUserRoleList.add(sUserRole);
            }
            boolean boo = userService.addRoleToUser(sUserRoleList);
            if (boo) {
                return ResultUtils.success("保存成功");
            } else {
                return ResultUtils.busiFail("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("UserAction%addRoleToUser", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "根据用户查询角色")
    @RequestMapping(value = "/queryRoleByUser", method = RequestMethod.POST)
    @ResponseBody
    public Object queryRoleByUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            String accessToken = RequestUtils.getSessionToken(request);
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(accessToken);
            if (cacheMember == null) {
                return ResultUtils.login();
            }
            //获取访问系统信息
            long ownerMid = RequestUtils.getSystemOwnerId(request);
            long userId = reqJson.getLongValue("userId");
            SUserRole sUserRole = new SUserRole();
            sUserRole.setUserId(userId);
            sUserRole.setOwnerMid(ownerMid);
            List<Map<String, Object>> data = userService.queryRoleByModel(sUserRole);
            return ResultUtils.list(data);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("UserAction%queryRoleByUser", e);
            return ResultUtils.exception();
        }
    }

}
