package com.ht.oa.jk.controller.security.action;

import com.ht.oa.jk.config.ConfigParam;
import com.ht.oa.jk.controller.security.service.ResourceService;
import com.ht.oa.jk.model.SResources;
import com.ht.oa.jk.model.TreeMenu;
import com.ht.oa.jk.model.code.ResultCode;
import com.ht.oa.jk.model.resp.CommonResponse;
import com.ht.oa.jk.utils.common.DateUtils;
import com.ht.oa.jk.utils.log.LogUtils;
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
@RequestMapping("/resource")
public class ResourceAction {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/home.htm")
    public String home() {
        return "security/resources";
    }

    /**
     * 添加菜单
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(SResources sResources) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            Date now = DateUtils.getNowDate();
            sResources.setInsertTime(now);
            sResources.setLastTime(now);
            sResources.setStatus(1);
            int res = resourceService.add(sResources);
            if (res > 0) {
                commonResponse.setCode(ResultCode.success.code());
            } else {
                commonResponse.setCode(ResultCode.busiError.code());
                commonResponse.setResMsg(ResultCode.busiError.desc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("ResourceAction%add", e);
        }
        return commonResponse;
    }

    /**
     * 查询菜单
     */
    @RequestMapping(method = RequestMethod.POST, value = "/showMenu")
    @ResponseBody
    public Object showMenu() {
        CommonResponse commonResponse = new CommonResponse();
        int userId = 0;
        try {
            List<SResources> list = resourceService.getAllResources(userId);
            if (ConfigParam.runMode == 1) {//如果是正式环境则去掉菜单管理
                if (list != null && list.size() > 0) {
                    for (SResources sResources : list) {
                        if ("菜单管理".equals(sResources.getResourceName())) {
                            list.remove(sResources);
                            break;
                        }
                    }
                }
            }
            TreeMenu root = new TreeMenu();
            root.setLeaf(false);
            root.setId(0);
            root.setText("根节点");
            root.setParentId(-1);
            root.setDescription("根节点");
            root.setHrefTarget("/*");
            root.setPriority(1);
            createTree(root, list);
            commonResponse.setData(root);
            commonResponse.setCode(ResultCode.success.code());
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("ResourceAction%showMenu", e);
        }
        return commonResponse;
    }

    /**
     * 查询所有的资源
     */
    @RequestMapping(method = RequestMethod.POST, value = "/showAllMenu")
    @ResponseBody
    public Object showAllMenu() {
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<SResources> list = resourceService.getAllResources();
            if (ConfigParam.runMode == 1) {//如果是正式环境则去掉菜单管理
                if (list != null && list.size() > 0) {
                    for (SResources sResources : list) {
                        if ("菜单管理".equals(sResources.getResourceName())) {
                            list.remove(sResources);
                            break;
                        }
                    }
                }
            }
            TreeMenu root = new TreeMenu();
            root.setLeaf(false);
            root.setId(0);
            root.setText("根节点");
            root.setParentId(-1);
            root.setDescription("根节点");
            root.setHrefTarget("/*");
            root.setPriority(1);
            createTree(root, list);
            commonResponse.setData(root);
            commonResponse.setCode(ResultCode.success.code());
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("ResourceAction%showAllMenu", e);
        }
        return commonResponse;
    }

    void createTree(TreeMenu root, List<SResources> totalList) {
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

    List<TreeMenu> listTreeChildren(long nodeId, List<SResources> totalList) {
        List<TreeMenu> result = new ArrayList<TreeMenu>();
        for (SResources item : totalList) {
            if (item.getParentId() == nodeId) {
                TreeMenu menu = new TreeMenu();
                menu.setId(item.getResourceId());
                menu.setDescription(item.getResourceDesc());
                menu.setHrefTarget(item.getResourceString());
                menu.setLeaf(item.getLeaf());
                menu.setText(item.getResourceName());
                menu.setParentId(item.getParentId());
                menu.setPriority(item.getPriority());
                menu.setIconCls(item.getIcon());
                result.add(menu);
            }
        }
        return result;
    }

    void createCheckTree(TreeMenu root, List<SResources> totalList, List<SResources> checkList) {
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

    List<TreeMenu> listCheckTreeChildren(long nodeId, List<SResources> totalList, List<SResources> checkList) {
        List<TreeMenu> result = new ArrayList<TreeMenu>();
        for (SResources item : totalList) {
            if (item.getParentId() == nodeId) {
                TreeMenu menu = new TreeMenu();
                menu.setId(item.getResourceId());
                menu.setDescription(item.getResourceDesc());
                menu.setHrefTarget(item.getResourceString());
                menu.setLeaf(item.getLeaf());
                menu.setText(item.getResourceName());
                menu.setParentId(item.getParentId());
                menu.setPriority(item.getPriority());
                for (SResources checkItem : checkList) {
                    if (checkItem.getResourceId().intValue() == item.getResourceId().intValue()) {
                        menu.setChecked(true);
                    }
                }
                result.add(menu);
            }
        }
        return result;
    }

    /**
     * 修改资源保存
     */
    @RequestMapping("/updateResource")
    @ResponseBody
    public Object updateResource(SResources model) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            int res = resourceService.updateResource(model);
            if (res > 0) {
                commonResponse.setCode(ResultCode.success.code());
            } else {
                commonResponse.setCode(ResultCode.busiError.code());
                commonResponse.setResMsg(ResultCode.busiError.desc());
            }
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("ResourceAction%updateResource", e);
        }
        return commonResponse;
    }

    /**
     * 查询菜单通过角色
     */
    @RequestMapping("/showMenuByRole.htm")
    @ResponseBody
    public Object showMenuByRole(HttpServletRequest request) {
        String roleId = request.getParameter("roleId");
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<SResources> allMenu = resourceService.getAllResources();
            if (ConfigParam.runMode == 1) {//如果是正式环境则去掉菜单管理
                if (allMenu != null && allMenu.size() > 0) {
                    for (SResources sResources : allMenu) {
                        if ("菜单管理".equals(sResources.getResourceName())) {
                            allMenu.remove(sResources);
                            break;
                        }
                    }
                }
            }
            List<SResources> list = resourceService.getResourcesByRoleId(Long.parseLong(roleId));
            TreeMenu root = new TreeMenu();
            root.setLeaf(false);
            root.setId(0);
            root.setText("根节点");
            root.setParentId(-1);
            root.setDescription("根节点");
            root.setHrefTarget("/*");
            root.setPriority(1);
            for (SResources item : list) {
                if (item.getResourceId() == root.getId()) {
                    root.setChecked(true);
                }
            }
            createCheckTree(root, allMenu, list);
            commonResponse.setData(root);
            commonResponse.setCode(ResultCode.success.code());
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(ResultCode.failure.code());
            commonResponse.setResMsg(ResultCode.failure.desc());
            LogUtils.error("ResourceAction%showMenuByRole", e);
        }
        return commonResponse;
    }


}
