package com.ht.oa.jk.controller.security.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.config.ApiDesc;
import com.ht.oa.jk.controller.security.service.RoleService;
import com.ht.oa.jk.model.SRoleMenu;
import com.ht.oa.jk.model.SRoles;
import com.ht.oa.jk.model.req.SRolesReq;
import com.ht.oa.jk.utils.cache.CacheMember;
import com.ht.oa.jk.utils.cache.MemberCacheUtils;
import com.ht.oa.jk.utils.common.DateUtils;
import com.ht.oa.jk.utils.common.ResultUtils;
import com.ht.oa.jk.utils.common.StringUtils;
import com.ht.oa.jk.utils.log.LogUtils;
import com.ht.oa.jk.utils.seq.IdWorker;
import com.ht.oa.jk.utils.system.RequestUtils;
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
@RequestMapping("/role")
public class RoleAction {

    @Autowired
    private RoleService roleService;

    @ApiDesc(name = "查询角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Object list(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
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

    @ApiDesc(name = "添加角色")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            String accessToken = RequestUtils.getSessionToken(request);
            long ownerMid = RequestUtils.getSystemOwnerId(request);
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(accessToken);
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
            sRoles.setOwnerMid(ownerMid);
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

    @ApiDesc(name = "修改角色信息")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Object modify(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            String accessToken = RequestUtils.getSessionToken(request);
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(accessToken);
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
                return ResultUtils.param("该角色名称已经被使用,请输入其他名称");
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

    @ApiDesc(name = "删除角色")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            String accessToken = RequestUtils.getSessionToken(request);
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(accessToken);
            long mid = cacheMember.getMid();
            long roleId = reqJson.getLongValue("roleId");
            //检查角色是否可删除
            if (!roleService.checkRoleIsEnabledDel(roleId)) {
                return ResultUtils.param("该角色已经被使用，不能删除");
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

    @ApiDesc(name = "添加菜单到角色")
    @RequestMapping(value = "/addMenuToRole", method = RequestMethod.POST)
    @ResponseBody
    public Object addMenuToRole(HttpServletRequest request, HttpServletResponse response) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            long roleId = reqJson.getLongValue("roleId");
            String menuIds = reqJson.getString("menuIds");
            String[] menuIdArr = menuIds.split(",");
            long[] menuIdList = new long[menuIdArr.length];
            for (int i = 0; i < menuIdArr.length; i++) {
                menuIdList[i] = Long.parseLong(menuIdArr[i]);
            }
            String sessionToken = RequestUtils.getSessionToken(request);
            CacheMember cacheMember = MemberCacheUtils.getCacheMember(sessionToken);
            long mid = cacheMember.getMid();
            long ownerMid = RequestUtils.getSystemOwnerId(request);
            List<SRoleMenu> sRoleResourceList = new ArrayList<>();
            Date now = DateUtils.getNowDate();
            for (long menuId : menuIdList) {
                SRoleMenu sRoleMenu = new SRoleMenu();
                sRoleMenu.setId(IdWorker.nextId());
                sRoleMenu.setMenuId(menuId);
                sRoleMenu.setRoleId(roleId);
                sRoleMenu.setInsertTime(now);
                sRoleMenu.setAddMid(mid);
                sRoleMenu.setOwnerMid(ownerMid);
                sRoleResourceList.add(sRoleMenu);
            }
            boolean boo = roleService.addMenusToRole(sRoleResourceList);
            if (boo) {
                return ResultUtils.success("成功");
            } else {
                return ResultUtils.busiFail("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("RoleAction%addMenuToRole", e);
            return ResultUtils.exception();
        }
    }

}
