package com.ht.oa.jk.controller.security.action;

import com.ht.oa.jk.controller.security.service.RoleService;
import com.ht.oa.jk.model.SRoleResource;
import com.ht.oa.jk.model.SRoles;
import com.ht.oa.jk.model.code.ResultCode;
import com.ht.oa.jk.model.resp.CommonResponse;
import com.ht.oa.jk.utils.auth.CacheMember;
import com.ht.oa.jk.utils.auth.LoginManage;
import com.ht.oa.jk.utils.common.DateUtils;
import com.ht.oa.jk.utils.common.ResultUtils;
import com.ht.oa.jk.utils.log.LogUtils;
import com.ht.oa.jk.utils.sso.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/home.htm")
    public String home() {
        return "security/role/list";
    }

    @RequestMapping("/show")
    @ResponseBody
    public Object show(HttpServletRequest request, HttpServletResponse response, SRoles sRoles) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            //获取访问系统信息
            String token = request.getParameter("token");
            long ownerMid = ApiUtils.getSystemOwnerId(token);
            if (ownerMid == 0) {
                return ResultUtils.getLoginResult();
            }

            sRoles.setOwnerMid(ownerMid);
            List<Map<String, Object>> list = roleService.queryAllRoles(sRoles);
            commonResponse.setData(list);
            commonResponse.setCode(ResultCode.success.code());
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("RoleAction%show", e);
        }
        return commonResponse;
    }

    /**
     * 添加角色
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(SRoles model, HttpServletRequest request, HttpServletResponse response) {
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

            Date now = DateUtils.getNowDate();
            model.setInsertTime(now);
            model.setLastTime(now);
            model.setStatus(1);
            model.setAddMid(mid);
            model.setUpdateMid(mid);
            model.setOwnerMid(ownerMid);
            int res = roleService.add(model);
            if (res > 0) {
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
            LogUtils.error("RoleAction%add", e);
        }
        return commonResponse;
    }

    /**
     * 修改角色
     */
    @RequestMapping("/modify")
    @ResponseBody
    public Object modify(SRoles sRoles, HttpServletRequest request, HttpServletResponse response) {
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

            sRoles.setOwnerMid(ownerMid);
            //检查角色是否可删除
            if (roleService.checkRoleNameIsExist(sRoles)) {
                commonResponse.setCode(ResultCode.busiError.code());
                commonResponse.setResMsg("该角色名称已经被使用,请输入其他名称！");
                return commonResponse;
            }
            Date now = new Date();
            sRoles.setLastTime(now);
            sRoles.setUpdateMid(mid);
            boolean boo = roleService.update(sRoles);
            if (boo) {
                commonResponse.setCode(ResultCode.success.code());
                commonResponse.setResMsg("修改成功");
            } else {
                commonResponse.setCode(ResultCode.busiError.code());
                commonResponse.setResMsg(ResultCode.busiError.desc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("RoleAction%modify", e);
        }
        return commonResponse;
    }

    /**
     * 删除角色
     */
    @RequestMapping("/del")
    @ResponseBody
    public Object del(HttpServletRequest request, HttpServletResponse response) {
        String rId = request.getParameter("rId");
        CommonResponse commonResponse = new CommonResponse();
        try {
            //检查角色是否可删除
            if (!roleService.checkRoleIsEnabledDel(Integer.parseInt(rId))) {
                commonResponse.setCode(ResultCode.busiError.code());
                commonResponse.setResMsg("该角色已经被使用，不能删除！");
                return commonResponse;
            }
            CacheMember cacheMember = LoginManage.getCacheMember(request, response);
            long mid = cacheMember.getMid();

            //获取访问系统信息
            String token = request.getParameter("token");
            long ownerMid = ApiUtils.getSystemOwnerId(token);
            if (ownerMid == 0) {
                return ResultUtils.getLoginResult();
            }

            Date now = DateUtils.getNowDate();
            SRoles sRoles = new SRoles();
            sRoles.setRoleId(Long.parseLong(rId));
            sRoles.setLastTime(now);
            sRoles.setUpdateMid(mid);
            sRoles.setOwnerMid(ownerMid);
            boolean boo = roleService.del(sRoles);
            if (boo) {
                commonResponse.setCode(ResultCode.success.code());
                commonResponse.setResMsg("删除成功");
            } else {
                commonResponse.setCode(ResultCode.busiError.code());
                commonResponse.setResMsg(ResultCode.busiError.desc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("RoleAction%del", e);
        }
        return commonResponse;
    }

    /**
     * 添加权限到角色
     */
    @RequestMapping("/addResourceToRole.htm")
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
            CacheMember cacheMember = LoginManage.getCacheMember(request, response);
            long mid = cacheMember.getMid();

            //获取访问系统信息
            String token = request.getParameter("token");
            long ownerMid = ApiUtils.getSystemOwnerId(token);
            if (ownerMid == 0) {
                return ResultUtils.getLoginResult();
            }

            List<SRoleResource> sRoleResourceList = new ArrayList<>();
            Date now = DateUtils.getNowDate();
            for (long resourceId : rIdList) {
                SRoleResource sRoleResource = new SRoleResource();
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

    /**
     * 选择菜单界面
     */
    @RequestMapping("/selectMenu.htm")
    public String selectMenu(HttpServletRequest request, ModelMap modelMap) {
        String roleId = request.getParameter("roleId");
        modelMap.put("roleId", roleId);
        return "security/role/select_menu";
    }


}
