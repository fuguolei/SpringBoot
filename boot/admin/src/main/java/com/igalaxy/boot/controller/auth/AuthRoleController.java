package com.igalaxy.boot.controller.auth;

import com.igalaxy.boot.controller.base.AdminController;
import com.igalaxy.boot.domain.auth.AuthRole;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.service.auth.AuthRoleService;
import com.igalaxy.boot.service.base.BaseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by fuguolei on 2017/2/3.
 */
@Controller
@RequestMapping("/auth/role")
public class AuthRoleController extends AdminController {

    @Autowired
    AuthRoleService authRoleService;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index() {
        writeLog("进入角色设置页面");
        return "auth/role";
    }

    @RequiresPermissions("auth:role:create")
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult add(AuthRole role, HttpServletResponse response) {
        BaseResult<AuthRole> result = authRoleService.save(role);
        writeLog(result, "添加角色:" + role.getName());
        return result;
    }

    @RequiresPermissions("auth:role:update")
    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult edit(AuthRole role, HttpServletResponse response) {
        BaseResult result = authRoleService.update(role);
        writeLog(result, "更新角色");
        return result;
    }

    @RequiresPermissions("auth:role:delete")
    @RequestMapping(value = "/delete.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(AuthRole role, HttpServletResponse response) {
        BaseResult result = authRoleService.delete(role);
        writeLog(result, "删除角色");
        return result;
    }

    @Override
    protected BaseService getService() {
        return authRoleService;
    }


}
