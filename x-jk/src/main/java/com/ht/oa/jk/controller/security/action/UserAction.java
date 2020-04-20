package com.ht.oa.jk.controller.security.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.controller.security.service.UserService;
import com.ht.oa.jk.model.SUserRole;
import com.ht.oa.jk.model.code.ResultCode;
import com.ht.oa.jk.model.resp.CommonResponse;
import com.ht.oa.jk.utils.auth.CacheMember;
import com.ht.oa.jk.utils.auth.LoginManage;
import com.ht.oa.jk.utils.common.DateUtils;
import com.ht.oa.jk.utils.common.ResultUtils;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.log.LogUtils;
import com.ht.oa.jk.utils.sso.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/list_view.htm")
    public String list_view() {
        return "security/user/list";
    }

    /**
     * 查询用户数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/query.htm")
    @ResponseBody
    public Object query(HttpServletRequest request, HttpServletResponse response) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            String token = request.getParameter("token");
            //获取访问系统信息
            long ownerMid = ApiUtils.getSystemOwnerId(token);
            if (ownerMid == 0) {
                return ResultUtils.getLoginResult();
            }
            String result = ApiUtils.getSystemPerson(token);
            if (StringUtils.isBlank(result)) {
                commonResponse.setCode(ResultCode.busiError.code());
                commonResponse.setResMsg(ResultCode.busiError.desc());
                return commonResponse;
            }
            JSONObject resJson = JSON.parseObject(result);
            if (resJson.getIntValue("code") == 200) {
                JSONArray list = resJson.getJSONArray("data");
                commonResponse.setCode(ResultCode.success.code());
                commonResponse.setData(list);
            } else {
                commonResponse.setCode(ResultCode.busiError.code());
                commonResponse.setResMsg(ResultCode.busiError.desc());
                return commonResponse;
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("UserAction%query", e);
        }
        return commonResponse;
    }

    /**
     * 添加角色到用户
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addRoleToUser.htm")
    @ResponseBody
    public Object addRoleToUser(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String rIds = request.getParameter("rIds");
        CommonResponse commonResponse = new CommonResponse();
        try {
            CacheMember cacheMember = LoginManage.getCacheMember(request, response);
            long mid = cacheMember.getMid();

            //获取访问系统信息
            String token = request.getParameter("token");
            long ownerMid = ApiUtils.getSystemOwnerId(token);
            if (ownerMid == 0) {
                return ResultUtils.getLoginResult();
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
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("UserAction%addRoleToUser", e);
        }
        return commonResponse;
    }

    /**
     * 根据用户查询角色
     */
    @RequestMapping(method = RequestMethod.POST, value = "/queryRoleByUser.htm")
    @ResponseBody
    public Object queryRoleByUser(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("id");
        CommonResponse commonResponse = new CommonResponse();
        try {
            //获取访问系统信息
            String token = request.getParameter("token");
            long ownerMid = ApiUtils.getSystemOwnerId(token);
            if (ownerMid == 0) {
                return ResultUtils.getLoginResult();
            }

            SUserRole sUserRole = new SUserRole();
            sUserRole.setUserId(Long.parseLong(userId));
            sUserRole.setOwnerMid(ownerMid);
            List<Map<String, Object>> data = userService.queryRoleByModel(sUserRole);
            commonResponse.setData(data);
            commonResponse.setCode(ResultCode.success.code());
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("UserAction%queryRoleByUser", e);
        }
        return commonResponse;
    }

}
