package com.igalaxy.boot.controller.auth;

import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.domain.auth.*;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.TreeViewNode;
import com.igalaxy.boot.enums.SysProperty.WhetherEnum;
import com.igalaxy.boot.service.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/auth/role/res")
public class AuthRoleResourceController extends BaseController {

    @Autowired
    AuthRoleService authRoleService;

    @Autowired
    AuthResourceService authResourceService;

    @Autowired
    AuthRoleResourceService authRoleResourceService;

    @Autowired
    AuthRolePermissionService authRolePermissionService;

    @Autowired
    AuthPermissionService authPermissionService;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index_jsp(long roleId, HttpServletRequest request) {
        request.setAttribute("roleId", roleId);
        AuthRole role = authRoleService.queryById(roleId);
        writeLog("进入 " + role.getName() + " 角色菜单设置页面");
        return "auth/resource";
    }

    @RequestMapping(value = "/getResource.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult getResource(long roleId) {
        List<AuthResource> list = authResourceService.selectAllResourceByRoleId(roleId);
        List<TreeViewNode> tree = new ArrayList<>();
        if (null != list && list.size() > 0) {
            for (AuthResource authResource : list) {
                if (authResource.getParentId() == null) {
                    TreeViewNode treeViewNode = new TreeViewNode();
                    treeViewNode.setId(authResource.getId());
                    treeViewNode.setText(authResource.getName());
                    treeViewNode.setType(1);
                    treeViewNode.setChecked(WhetherEnum.Yes.equals(authResource.getHasPermission()));
                    // List<EasyUITree> childNodes = new ArrayList<>();
                    for (AuthResource node : list) {
                        if (node.getParentId() == null) continue;
                        if (authResource.getId().longValue() == node.getParentId().longValue()) {
                            TreeViewNode childNode = new TreeViewNode();
                            childNode.setId(node.getId());
                            childNode.setText(node.getName());
                            childNode.setType(1);
                            childNode.setChecked(WhetherEnum.Yes.equals(node.getHasPermission()));
                            List<AuthPermission> permissions = node.getPermissions();
                            if (permissions != null && permissions.size() > 0) {
                                for (AuthPermission permission : permissions) {
                                    TreeViewNode permissionNode = new TreeViewNode();
                                    permissionNode.setChecked(WhetherEnum.Yes.equals(permission.getHasPermission()));
                                    permissionNode.setText(permission.getName());
                                    permissionNode.setId(permission.getId());
                                    permissionNode.setType(0);
                                    if (childNode.getNodes() == null)
                                        childNode.setNodes(new ArrayList<>());
                                    childNode.getNodes().add(permissionNode);
                                }
                            }
                            if (treeViewNode.getNodes() == null)
                                treeViewNode.setNodes(new ArrayList<>());
                            treeViewNode.getNodes().add(childNode);
                        }
                    }
                    tree.add(treeViewNode);
                }
            }
        }
        return BaseResult.ok(tree);
    }

    @RequestMapping(value = "/delete.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(Long roleId, Long id, int type, HttpServletResponse response) {
        BaseResult result;
        String resOrPer = null, strRole = null, strResPer = null;
        if (type == 1) {
            result = authRoleResourceService.deleteByRoleIdResourceId(roleId, id);
            resOrPer = "菜单";
            strRole = authRoleService.queryById(roleId).getName();
            strResPer = authResourceService.queryById(id).getName();
        } else if (type == 0) {
            result = authRolePermissionService.deleteByRoleIdPermissionId(roleId, id);
            resOrPer = "权限";
            strRole = authRoleService.queryById(roleId).getName();
            strResPer = authPermissionService.queryById(id).getName();
        } else {
            result = BaseResult.badRequest("类型不匹配");
        }

        writeLog(result, String.format("删除角色 [%s] 中的 [%s][%s]", resOrPer, strRole, strResPer));
        return result;
    }

    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult add(Long roleId, Long id, int type, HttpServletResponse response) {
        BaseResult result;
        String resOrPer = null, strRole = null, strResPer = null;
        if (type == 1) {
            AuthRoleResource resource = new AuthRoleResource();
            resource.setResourceId(id);
            resource.setRoleId(roleId);
            resource.setCreateUser(getUserId());
            result = authRoleResourceService.save(resource);
            AuthRoleResource res = authRoleResourceService.queryById(((BaseDomain) result.getData()).getId());
            resOrPer = "菜单";
            strRole = res.getRoleName();
            strResPer = res.getResourceName();
        } else if (type == 0) {
            AuthRolePermission permission = new AuthRolePermission();
            permission.setPermissionId(id);
            permission.setRoleId(roleId);
            permission.setCreateUser(getUserId());
            result = authRolePermissionService.save(permission);
            AuthRolePermission per = authRolePermissionService.queryById(((BaseDomain) result.getData()).getId());
            resOrPer = "权限";
            strRole = per.getRoleName();
            strResPer = per.getPermissionName();
        } else {
            result = BaseResult.badRequest("类型不匹配");
        }
//        writeLog(result, String.format("删除角色 [%s] 中的 [%s][%s]", resOrPer, strRole, strResPer));
        return result;
    }
}
