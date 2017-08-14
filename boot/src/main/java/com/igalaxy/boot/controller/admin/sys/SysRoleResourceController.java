package com.igalaxy.boot.controller.admin.sys;

import com.igalaxy.boot.controller.admin.base.AdminController;
import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.EasyUITree;
import com.igalaxy.boot.domain.sys.*;
import com.igalaxy.boot.enums.SysProperty.WhetherEnum;
import com.igalaxy.boot.service.sys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinlong on 2017/2/5.
 */
@Controller
@RequestMapping("/admin/sys/role/res")
public class SysRoleResourceController extends AdminController {

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysResourceService sysResourceService;

    @Autowired
    SysRoleResourceService sysRoleResourceService;

    @Autowired
    SysRolePermissionService sysRolePermissionService;

    @Autowired
    SysPermissionService sysPermissionService;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index_jsp(long roleId, HttpServletRequest request) {
        request.setAttribute("roleId", roleId);
        SysRole role = sysRoleService.queryById(roleId);
        writeLog("进入 " + role.getName() + " 角色菜单设置页面");
        return "sys/roleResource";
    }

    @RequestMapping(value = "/getAllRoleTree.json", method = RequestMethod.POST)
    public String getAllRoleTree(long roleId, HttpServletResponse response, HttpServletRequest request) {
        List<SysResource> list = sysResourceService.selectAllResourceByRoleId(roleId);
        List<EasyUITree> tree = new ArrayList<>();
        if (null != list && list.size() > 0) {
            for (SysResource category : list) {
                if (category.getParentId() == null) {
                    EasyUITree categoryNode = new EasyUITree();
                    categoryNode.setId(category.getId());
                    categoryNode.setText(category.getName());
                    categoryNode.setState("open");
                    categoryNode.setChecked(WhetherEnum.Yes.equals(category.getHasPermission()));
                    categoryNode.setAttributes(category);
                    // List<EasyUITree> childNodes = new ArrayList<>();
                    for (SysResource node : list) {
                        if (node.getParentId() == null) continue;
                        if (category.getId().longValue() == node.getParentId().longValue()) {
                            EasyUITree childNode = new EasyUITree();
                            childNode.setId(node.getId());
                            childNode.setText(node.getName());
                            childNode.setChecked(WhetherEnum.Yes.equals(node.getHasPermission()));
                            childNode.setAttributes(node);
                            List<SysPermission> permissions = node.getPermissions();
                            if (permissions != null && permissions.size() > 0) {
                                for (SysPermission permission : permissions) {
                                    EasyUITree permissionNode = new EasyUITree();
                                    permissionNode.setChecked(WhetherEnum.Yes.equals(permission.getHasPermission()));
                                    permissionNode.setState("open");
                                    permissionNode.setText(permission.getName());
                                    permissionNode.setId(permission.getId());
                                    permissionNode.setAttributes(permission);
                                    childNode.getChildren().add(permissionNode);
                                }
                            }
                            categoryNode.getChildren().add(childNode);
                        }
                    }
                    tree.add(categoryNode);
                }
            }
        }

        return writeResultOld(response, tree);
    }

    @RequestMapping(value = "/deleteRoleResource.json", method = RequestMethod.POST)
    public String deleteRoleResource(Long roleId, Long id, int type, HttpServletResponse response) {
        BaseResult result;
        String resOrPer = null, strRole = null, strResPer = null;
        if (type == 1) {
            result = sysRoleResourceService.deleteByRoleIdResourceId(roleId, id);
            resOrPer = "菜单";
            strRole = sysRoleService.queryById(roleId).getName();
            strResPer = sysResourceService.queryById(id).getName();
        } else if (type == 0) {
            result = sysRolePermissionService.deleteByRoleIdPermissionId(roleId, id);
            resOrPer = "权限";
            strRole = sysRoleService.queryById(roleId).getName();
            strResPer = sysPermissionService.queryById(id).getName();
        } else {
            result = new BaseResult(false, "类型不匹配");
        }

        writeLog(result, String.format("删除角色 [%s] 中的 [%s][%s]", resOrPer, strRole, strResPer));
        return writeResult(response, result);
    }

    @RequestMapping(value = "/addRoleResource.json", method = RequestMethod.POST)
    public String addRoleResource(Long roleId, Long id, int type, HttpServletResponse response) {
        BaseResult result;
        String resOrPer = null, strRole = null, strResPer = null;
        if (type == 1) {
            SysRoleResource resource = new SysRoleResource();
            resource.setResourceId(id);
            resource.setRoleId(roleId);
            resource.setCreateUser(getUserId());
            result = sysRoleResourceService.save(resource);
            SysRoleResource res = sysRoleResourceService.queryById(((BaseDomain) result.getData()).getId());
            resOrPer = "菜单";
            strRole = res.getRoleName();
            strResPer = res.getResourceName();
        } else if (type == 0) {
            SysRolePermission permission = new SysRolePermission();
            permission.setPermissionId(id);
            permission.setRoleId(roleId);
            permission.setCreateUser(getUserId());
            result = sysRolePermissionService.save(permission);
            SysRolePermission per = sysRolePermissionService.queryById(((BaseDomain) result.getData()).getId());
            resOrPer = "权限";
            strRole = per.getRoleName();
            strResPer = per.getPermissionName();
        } else {
            result = new BaseResult(false, "类型不匹配");
        }
//        writeLog(result, String.format("删除角色 [%s] 中的 [%s][%s]", resOrPer, strRole, strResPer));
        return writeResult(response, result);
    }
}
