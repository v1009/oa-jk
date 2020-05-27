package com.ht.oa.jk.controller.security.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.config.ApiDesc;
import com.ht.oa.jk.controller.security.service.RoleService;
import com.ht.oa.jk.model.SRoleResources;
import com.ht.oa.jk.model.SRoles;
import com.ht.oa.jk.model.req.SRolesReq;
import com.ht.oa.jk.model.resp.CommonResponse;
import com.ht.oa.jk.utils.auth.AuthTools;
import com.ht.oa.jk.utils.cache.CacheMember;
import com.ht.oa.jk.utils.cache.MemberCacheUtils;
import com.ht.oa.jk.utils.code.ResultCode;
import com.ht.oa.jk.utils.common.DateUtils;
import com.ht.oa.jk.utils.common.ResultUtils;
import com.ht.oa.jk.utils.common.StringUtils;
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
@RequestMapping("/role")
public class RoleAction {

    @Autowired
    private RoleService roleService;

    @ApiDesc(code = "/list", name = "查询角色列表")
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
            String roleName = reqJson.getString("roleName");
            Integer page = reqJson.getInteger("page");
            if (page == null) {
                page = 1;
            }
            SRolesReq sRolesReq = new SRolesReq();
            sRolesReq.setPage(page);
            sRolesReq.setRoleName(roleName);
            List<Map<String, Object>> list = roleService.list(sRolesReq);
            return ResultUtils.list(list);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("RoleAction%list", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(code = "/add", name = "添加角色")
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
            long mid = cacheMember.getMid();
            String roleName = reqJson.getString("roleName");
            Date now = DateUtils.getNowDate();
            SRoles sRoles = new SRoles();
            sRoles.setRoleId(IdWorker.nextId());
            sRoles.setRoleName(roleName);
            sRoles.setInsertTime(now);
            sRoles.setLastTime(now);
            sRoles.setStatus(1);
            sRoles.setAddMid(mid);
            sRoles.setUpdateMid(mid);
            sRoles.setOwnerMid(0L);
            boolean boo = roleService.add(sRoles);
            if (boo) {
                return ResultUtils.success("添加成功");
            } else {
                return ResultUtils.busiFail("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("RoleAction%add", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(code = "/modify", name = "修改角色信息")
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
            long mid = cacheMember.getMid();
            long roleId = reqJson.getLongValue("roleId");
            String roleName = reqJson.getString("roleName");
            Date now = DateUtils.getNowDate();
            SRoles sRoles = new SRoles();
            sRoles.setRoleId(roleId);
            sRoles.setRoleName(roleName);
            sRoles.setLastTime(now);
            sRoles.setStatus(1);
            sRoles.setUpdateMid(mid);
            //检查角色名称是否被使用
            if (roleService.checkRoleNameIsExist(sRoles)) {
                return ResultUtils.paramNoPass("该角色名称已经被使用,请输入其他名称");
            }
            boolean boo = roleService.modify(sRoles);
            if (boo) {
                return ResultUtils.success("修改成功");
            } else {
                return ResultUtils.busiFail("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("RoleAction%modify", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(code = "/del", name = "删除角色")
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
            long mid = cacheMember.getMid();
            long roleId = reqJson.getLongValue("roleId");
            //检查角色是否可删除
            if (!roleService.checkRoleIsEnabledDel(roleId)) {
                return ResultUtils.paramNoPass("该角色已经被使用，不能删除");
            }
            Date now = DateUtils.getNowDate();
            SRoles sRoles = new SRoles();
            sRoles.setRoleId(roleId);
            sRoles.setLastTime(now);
            sRoles.setStatus(1);
            sRoles.setUpdateMid(mid);
            boolean boo = roleService.del(sRoles);
            if (boo) {
                return ResultUtils.success("删除成功");
            } else {
                return ResultUtils.busiFail("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("RoleAction%del", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(code = "/addResourceToRole", name = "添加权限到角色")
    @RequestMapping(value = "/addResourceToRole", method = RequestMethod.POST)
    @ResponseBody
    public Object addResourceToRole(HttpServletRequest request, HttpServletResponse response) {
        String roleId = request.getParameter("roleId");
        String rIds = request.getParameter("rIds");
        CommonResponse commonResponse = new CommonResponse();
        try {
            String[] rIdArr = rIds.split(",");
            long[] rIdList = new long[rIdArr.length];
            for (int i = 0; i < rIdArr.length; i++) {
                rIdList[i] = Long.parseLong(rIdArr[i]);
            }
            long mid = SystemUtils.getMid(request);
            //获取访问系统信息
            String token = SystemUtils.getRequestToken(request);
            long ownerMid = ApiUtils.getSystemOwnerId(token);
            if (ownerMid == 0) {
                return ResultUtils.login();
            }

            List<SRoleResources> sRoleResourceList = new ArrayList<>();
            Date now = DateUtils.getNowDate();
            for (long resourceId : rIdList) {
                SRoleResources sRoleResource = new SRoleResources();
                sRoleResource.setResourceId(resourceId);
                sRoleResource.setRoleId(Long.parseLong(roleId));
                sRoleResource.setInsertTime(now);
                sRoleResource.setAddMid(mid);
                sRoleResource.setOwnerMid(ownerMid);
                sRoleResourceList.add(sRoleResource);
            }

            boolean boo = roleService.addResourceToRole(sRoleResourceList);
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
            LogUtils.error("RoleAction%addResourceToRole", e);
        }
        return commonResponse;
    }

}
