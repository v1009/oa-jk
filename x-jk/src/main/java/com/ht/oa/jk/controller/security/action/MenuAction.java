package com.ht.oa.jk.controller.security.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.oa.jk.config.ApiDesc;
import com.ht.oa.jk.controller.security.service.MenuService;
import com.ht.oa.jk.model.SMenu;
import com.ht.oa.jk.model.SRoleMenu;
import com.ht.oa.jk.model.TreeMenu;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuAction {

    @Autowired
    private MenuService menuService;

    @ApiDesc(name = "添加菜单")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(HttpServletRequest request) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            long parentId = reqJson.getLongValue("parentId");
            String name = reqJson.getString("name");
            String path = reqJson.getString("path");
            String desc = reqJson.getString("desc");
            String icon = reqJson.getString("icon");
            Date now = DateUtils.getNowDate();
            SMenu menu = new SMenu();
            menu.setMenuId(IdWorker.nextId());
            menu.setParentId(parentId);
            menu.setMenuName(name);
            menu.setMenuType(1);
            menu.setMenuPath(path);
            menu.setMenuDesc(desc);
            menu.setIcon(icon);
            menu.setLeaf(1);
            menu.setInsertTime(now);
            menu.setLastTime(now);
            menu.setStatus(1);
            boolean boo = menuService.add(menu);
            if (boo) {
                return ResultUtils.success("添加成功");
            } else {
                return ResultUtils.busiFail("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("MenuAction%add", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "修改菜单")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public Object modify(HttpServletRequest request) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            long id = reqJson.getLongValue("id");
            String name = reqJson.getString("name");
            String path = reqJson.getString("path");
            String desc = reqJson.getString("desc");
            String icon = reqJson.getString("icon");
            Date now = DateUtils.getNowDate();
            SMenu menu = new SMenu();
            menu.setMenuId(id);
            menu.setMenuName(name);
            menu.setMenuType(1);
            menu.setMenuPath(path);
            menu.setMenuDesc(desc);
            menu.setIcon(icon);
            menu.setInsertTime(now);
            menu.setLastTime(now);
            menu.setStatus(1);
            boolean boo = menuService.modify(menu);
            if (boo) {
                return ResultUtils.success("修改成功");
            } else {
                return ResultUtils.busiFail("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("MenuAction%modify", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "查询指定用户的菜单")
    @RequestMapping(value = "/findUserMenu", method = RequestMethod.POST)
    @ResponseBody
    public Object findUserMenu(HttpServletRequest request) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            long userId = reqJson.getLongValue("userId");
            List<SMenu> list = menuService.queryAllMenus(userId);
            TreeMenu root = new TreeMenu();
            root.setLeaf(false);
            root.setId(0L);
            root.setLabel("根节点");
            root.setParentId(-1L);
            root.setPath("/*");
            root.setPriority(1);
            createTree(root, list);
            return ResultUtils.model(root.getChildren());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("MenuAction%findUserMenu", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "查询所有菜单通过角色")
    @RequestMapping(value = "/findAllMenuByRole", method = RequestMethod.POST)
    @ResponseBody
    public Object findAllMenuByRoleId(HttpServletRequest request) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            long roleId = reqJson.getLongValue("roleId");
            long ownerMid = RequestUtils.getSystemOwnerId(request);
            SRoleMenu sRoleMenu = new SRoleMenu();
            sRoleMenu.setRoleId(roleId);
            sRoleMenu.setOwnerMid(ownerMid);
            List<SMenu> list = menuService.getAllMenuByRoleId(sRoleMenu);
            TreeMenu root = new TreeMenu();
            root.setLeaf(false);
            root.setId(0L);
            root.setLabel("根节点");
            root.setParentId(-1L);
            root.setPath("/*");
            root.setPriority(1);
            createTree(root, list);
            return ResultUtils.model(root.getChildren());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("MenuAction%findAllMenuByRole", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "查询所有菜单")
    @RequestMapping(value = "/findAllMenu", method = RequestMethod.POST)
    @ResponseBody
    public Object findAllMenu(HttpServletRequest request) {
        try {
            List<SMenu> list = menuService.queryAllMenus();
            TreeMenu root = new TreeMenu();
            root.setLeaf(false);
            root.setId(0L);
            root.setLabel("根节点");
            root.setParentId(-1L);
            root.setPath("/*");
            root.setPriority(1);
            createTree(root, list);
            return ResultUtils.model(root.getChildren());
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("MenuAction%findAllMenu", e);
            return ResultUtils.exception();
        }
    }

    void createTree(TreeMenu root, List<SMenu> totalList) {
        //如果不是叶子节点
        if (!root.isLeaf()) {
            List<TreeMenu> children = listTreeChildren(root.getId(), totalList);
            if (children != null && children.size() > 0) {
                root.setChildren(children);
                for (TreeMenu item : children) {
                    createTree(item, totalList);
                }
            }
        }
    }

    List<TreeMenu> listTreeChildren(long nodeId, List<SMenu> totalList) {
        List<TreeMenu> result = new ArrayList<TreeMenu>();
        for (SMenu item : totalList) {
            if (item.getParentId() == nodeId) {
                TreeMenu menu = new TreeMenu();
                menu.setId(item.getMenuId());
                menu.setParentId(item.getParentId());
                menu.setLabel(item.getMenuName());
                menu.setPath(item.getMenuPath());
                menu.setLeaf(item.getLeaf() == 1);
                menu.setPriority(item.getPriority());
                menu.setIconCls(item.getIcon());
                menu.setChecked(item.getStatus() > 0);
                result.add(menu);
            }
        }
        return result;
    }

    void createCheckTree(TreeMenu root, List<SMenu> totalList, List<SMenu> checkList) {
        //如果不是叶子节点
        if (!root.isLeaf()) {
            List<TreeMenu> children = listCheckTreeChildren(root.getId(), totalList, checkList);
            if (children != null && children.size() > 0) {
                root.setChildren(children);
                for (TreeMenu item : children) {
                    createCheckTree(item, totalList, checkList);
                }
            }
        }
    }

    List<TreeMenu> listCheckTreeChildren(long nodeId, List<SMenu> totalList, List<SMenu> checkList) {
        List<TreeMenu> result = new ArrayList<TreeMenu>();
        for (SMenu item : totalList) {
            if (item.getParentId() == nodeId) {
                TreeMenu menu = new TreeMenu();
                menu.setId(item.getMenuId());
                menu.setPath(item.getMenuPath());
                menu.setLeaf(item.getLeaf() == 1);
                menu.setLabel(item.getMenuName());
                menu.setParentId(item.getParentId());
                menu.setPriority(item.getPriority());
                for (SMenu checkItem : checkList) {
                    if (checkItem.getMenuId().intValue() == item.getMenuId().intValue()) {
                        menu.setChecked(true);
                    }
                }
                result.add(menu);
            }
        }
        return result;
    }

    @ApiDesc(name = "查询菜单通过角色")
    @RequestMapping(value = "/findMenuByRole", method = RequestMethod.POST)
    @ResponseBody
    public Object findMenuByRole(HttpServletRequest request) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            long roleId = reqJson.getLongValue("roleId");
            List<SMenu> list = menuService.getMenuListByRoleId(roleId);
            TreeMenu root = new TreeMenu();
            root.setLeaf(false);
            root.setId(0L);
            root.setLabel("根节点");
            root.setParentId(-1L);
            root.setPath("/*");
            root.setPriority(1);
            createTree(root, list);
            return ResultUtils.model(root);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("MenuAction%findMenuByRole", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "启用菜单")
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @ResponseBody
    public Object enable(HttpServletRequest request) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            long id = reqJson.getLongValue("id");
            Date now = DateUtils.getNowDate();
            SMenu sMenu = new SMenu();
            sMenu.setMenuId(id);
            sMenu.setLastTime(now);
            boolean boo = menuService.enable(sMenu);
            if (boo) {
                return ResultUtils.success("启动成功");
            } else {
                return ResultUtils.busiFail("启动失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("MenuAction%enable", e);
            return ResultUtils.exception();
        }
    }

    @ApiDesc(name = "停用菜单")
    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    @ResponseBody
    public Object stop(HttpServletRequest request) {
        try {
            String requestMsg = RequestUtils.getRequestBody(request);
            if (StringUtils.isBlank(requestMsg)) {
                return ResultUtils.param("参数必传");
            }
            JSONObject reqJson = JSON.parseObject(requestMsg);
            long id = reqJson.getLongValue("id");
            Date now = DateUtils.getNowDate();
            SMenu sMenu = new SMenu();
            sMenu.setMenuId(id);
            sMenu.setLastTime(now);
            boolean boo = menuService.stop(sMenu);
            if (boo) {
                return ResultUtils.success("停用成功");
            } else {
                return ResultUtils.busiFail("停用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.error("MenuAction%stop", e);
            return ResultUtils.exception();
        }
    }

}
