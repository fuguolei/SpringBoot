package com.igalaxy.boot.controller.sys;

import com.igalaxy.boot.controller.base.BaseController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.sys.SysRole;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.domain.sys.SysUserRole;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.service.sys.SysRoleService;
import com.igalaxy.boot.service.sys.SysUserRoleService;
import com.igalaxy.boot.service.usr.UsrUserService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by jinlong on 2017/2/5.
 */
@Controller
@RequestMapping("/admin/sys/role/user")
public class SysUserRoleController extends BaseController {

    @Resource
    SysUserRoleService sysUserRoleService;

    @Resource
    SysRoleService sysRoleService;

    @Resource
    UsrUserService usrUserService;

    @Override
    protected BaseService getService() {
        return sysUserRoleService;
    }

    @Override
    protected Map<String, Object> getOtherParams() {
        Map<String, Object> map = new HashedMap();
        String roleId = getServletRequest().getParameter("roleId");
        map.put("roleId", roleId);
        return map;
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index_jsp(Integer roleId, HttpServletRequest request) {
        request.setAttribute("roleId", roleId);
        writeLog("进入角色人员设置页面");
        return "sys/userRole";
    }

    @RequestMapping(value = "/getNoUserInRoleList.html", method = RequestMethod.POST)
    public String selectNoUserInRole(long roleId, HttpServletResponse response) {
        List<SysUserRole> list = sysUserRoleService.selectNoUserInRole(roleId);
        return writeResultOld(response, list);
    }

    @RequestMapping(value = "/addUserRole.html", method = RequestMethod.POST)
    public String addUserRole(SysUserRole userRole, HttpServletResponse response) {
        BaseResult<SysUserRole> result = sysUserRoleService.save(userRole);
        SysRole role = sysRoleService.queryById(userRole.getRoleId());
        UsrUser usrUser = usrUserService.queryById(userRole.getUserId());
        writeLog(result, String.format("从角色[%s]添加用户[%s]", role.getName(), usrUser.getName()));
        return writeResult(response, result);
    }

    @RequestMapping(value = "/deleteUserRole.html", method = RequestMethod.POST)
    public String deleteRole(SysUserRole userRole, HttpServletResponse response) {
        BaseResult result = sysUserRoleService.delete(userRole);
        SysUserRole ul = sysUserRoleService.queryById(userRole.getId());
        writeLog(result, String.format("从角色[%s]中删除用户[%s]", ul.getRoleName(), ul.getUserName()));
        return writeResult(response, result);
    }
}
