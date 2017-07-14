package com.igalaxy.boot.controller.sys;

import com.igalaxy.boot.controller.base.BaseController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.sys.SysRole;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.service.sys.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by jinlong on 2017/2/3.
 */
@Controller
@RequestMapping("/admin/sys/role")
public class SysRoleController extends BaseController {

    @Autowired
    SysRoleService sysRoleService;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index_jsp() {
        writeLog("进入角色设置页面");
        return "sys/role";
    }

    @RequiresPermissions("sys:role:create")
    @RequestMapping(value = "/addRole.json", method = RequestMethod.POST)
    public String addRole(SysRole role, HttpServletResponse response) {
        BaseResult<SysRole> result = sysRoleService.save(role);
        writeLog(result, "添加角色:" + role.getName());
        return writeResult(response, result);
    }

    @RequiresPermissions("sys:role:update")
    @RequestMapping(value = "/editRole.json", method = RequestMethod.POST)
    public String editRole(SysRole role, HttpServletResponse response) {
        BaseResult result = sysRoleService.update(role);
        writeLog(result, "更新角色");
        return writeResult(response, result);
    }

    @RequiresPermissions("sys:role:delete")
    @RequestMapping(value = "/deleteRole.json", method = RequestMethod.POST)
    public String deleteRole(SysRole role, HttpServletResponse response) {
        BaseResult result = sysRoleService.delete(role);
        writeLog(result, "删除角色");
        return writeResult(response, result);
    }

    @Override
    protected BaseService getService() {
        return sysRoleService;
    }


}
