package com.ht.oa.jk.controller.security.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.config.ApiDesc;
import com.ht.oa.jk.controller.security.service.UserService;
import com.ht.oa.jk.model.SUserRole;
import com.ht.oa.jk.model.SUsers;
import com.ht.oa.jk.model.req.SUsersReq;
import com.ht.oa.jk.model.resp.CommonResponse;
import com.ht.oa.jk.utils.auth.AuthTools;
import com.ht.oa.jk.utils.cache.CacheMember;
import com.ht.oa.jk.utils.cache.MemberCacheUtils;
import com.ht.oa.jk.utils.code.ResultCode;
import com.ht.oa.jk.utils.common.DateUtils;
import com.ht.oa.jk.utils.common.ResultUtils;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.crypto.PwdUtils;
import com.ht.oa.jk.utils.log.LogUtils;
import com.ht.oa.jk.utils.seq.IdWorker;
import com.ht.oa.jk.utils.sso.ApiUtils;
import com.ht.oa.jk.utils.system.SystemUtils;
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

    @ApiDesc(code = "/add", name = "添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(HttpServletRequest request, HttpServletResponse response) {
        ServletInputStream in = null;
        try {
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
            String accessToken = AuthTools.getToken(request);
            if (StringUtils.isBlank(accessToken)) {
                return ResultUtils.paramNoPass("accessToken不能为空");
            }
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(accessToken);
            if (cacheMember == null) {
                return ResultUtils.login();
            }
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

    @ApiDesc(code = "/list", name = "查询用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(HttpServletRequest request, HttpServletResponse response) {
        ServletInputStream in = null;
        try {
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
            String accessToken = AuthTools.getToken(request);
            if (StringUtils.isBlank(accessToken)) {
                return ResultUtils.paramNoPass("accessToken不能为空");
            }
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(accessToken);
            if (cacheMember == null) {
                return ResultUtils.login();
            }
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

    @ApiDesc(code = "/modify", name = "修改用户信息")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Object modify(HttpServletRequest request, HttpServletResponse response) {
        ServletInputStream in = null;
        try {
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
            String accessToken = AuthTools.getToken(request);
            if (StringUtils.isBlank(accessToken)) {
                return ResultUtils.paramNoPass("accessToken不能为空");
            }
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(accessToken);
            if (cacheMember == null) {
                return ResultUtils.login();
            }
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

    @ApiDesc(code = "/del", name = "删除用户")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(HttpServletRequest request, HttpServletResponse response) {
        ServletInputStream in = null;
        try {
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
            String accessToken = AuthTools.getToken(request);
            if (StringUtils.isBlank(accessToken)) {
                return ResultUtils.paramNoPass("accessToken不能为空");
            }
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(accessToken);
            if (cacheMember == null) {
                return ResultUtils.login();
            }
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

    @ApiDesc(code = "/findModelByUserId", name = "查询用户信息")
    @RequestMapping(value = "/findModelByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Object findModelByUserId(HttpServletRequest request, HttpServletResponse response) {
        ServletInputStream in = null;
        try {
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
            String accessToken = AuthTools.getToken(request);
            if (StringUtils.isBlank(accessToken)) {
                return ResultUtils.paramNoPass("accessToken不能为空");
            }
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

    @ApiDesc(code = "/addRoleToUser", name = "授权角色给用户")
    @RequestMapping(value = "/addRoleToUser", method = RequestMethod.POST)
    @ResponseBody
    public Object addRoleToUser(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String rIds = request.getParameter("rIds");
        CommonResponse commonResponse = new CommonResponse();
        try {
            long mid = SystemUtils.getMid(request);
            //获取访问系统信息
            String token = SystemUtils.getRequestToken(request);
            long ownerMid = ApiUtils.getSystemOwnerId(token);
            if (ownerMid == 0) {
                return ResultUtils.login();
            }
            String[] rIdArr = rIds.split(",");
            long[] rIdList = new long[rIdArr.length];
            for (int i = 0; i < rIdArr.length; i++) {
                rIdList[i] = Long.parseLong(rIdArr[i]);
            }
            List<SUserRole> sUserRoleList = new ArrayList<>();
            Date now = DateUtils.getNowDate();
            for (long roleId : rIdList) {
                SUserRole sUserRole = new SUserRole();
                sUserRole.setUserId(Long.parseLong(userId));
                sUserRole.setRoleId(roleId);
                sUserRole.setInsertTime(now);
                sUserRole.setAddMid(mid);
                sUserRole.setOwnerMid(ownerMid);
                sUserRoleList.add(sUserRole);
            }

            boolean boo = userService.addRoleToUser(sUserRoleList);
            if (boo) {
                commonResponse.setCode(ResultCode.success.code());
                commonResponse.setResMsg("保存成功");
            } else {
                commonResponse.setCode(ResultCode.busiError.code());
                commonResponse.setResMsg(ResultCode.busiError.desc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("UserAction%addRoleToUser", e);
            return ResultUtils.exception();
        }
        return commonResponse;
    }

    @ApiDesc(code = "/queryRoleByUser", name = "根据用户查询角色")
    @RequestMapping(value = "/queryRoleByUser", method = RequestMethod.POST)
    @ResponseBody
    public Object queryRoleByUser(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("id");
        CommonResponse commonResponse = new CommonResponse();
        try {
            //获取访问系统信息
            String token = SystemUtils.getRequestToken(request);
            long ownerMid = ApiUtils.getSystemOwnerId(token);
            if (ownerMid == 0) {
                return ResultUtils.login();
            }
            SUserRole sUserRole = new SUserRole();
            sUserRole.setUserId(Long.parseLong(userId));
            sUserRole.setOwnerMid(ownerMid);
            List<Map<String, Object>> data = userService.queryRoleByModel(sUserRole);
            commonResponse.setData(data);
            commonResponse.setCode(ResultCode.success.code());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("UserAction%queryRoleByUser", e);
            return ResultUtils.exception();
        }
        return commonResponse;
    }

}
