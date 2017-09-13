package com.igalaxy.boot.controller.auth;

import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.domain.auth.AuthRole;
import com.igalaxy.boot.domain.auth.AuthUserRole;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.service.auth.AuthRoleService;
import com.igalaxy.boot.service.auth.AuthUserRoleService;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.service.usr.UsrUserService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth/role/user")
public class AuthUserRoleController extends BaseController {

    @Resource
    AuthUserRoleService authUserRoleService;

    @Resource
    AuthRoleService authRoleService;

    @Resource
    UsrUserService usrUserService;

    @Override
    protected BaseService getService() {
        return authUserRoleService;
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
        return "auth/userRole";
    }

    @RequestMapping(value = "/getNoUserInRoleList.html", method = RequestMethod.POST)
    public String selectNoUserInRole(long roleId, HttpServletResponse response) {
        List<AuthUserRole> list = authUserRoleService.selectNoUserInRole(roleId);
        return null;
    }

    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult add(AuthUserRole userRole, HttpServletResponse response) {
        BaseResult<AuthUserRole> result = authUserRoleService.save(userRole);
        AuthRole role = authRoleService.queryById(userRole.getRoleId());
        UsrUser usrUser = usrUserService.queryById(userRole.getUserId());
        writeLog(result, String.format("从角色[%s]添加用户[%s]", role.getName(), usrUser.getName()));
        return result;
    }

    @RequestMapping(value = "/delete.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(AuthUserRole userRole, HttpServletResponse response) {
        BaseResult result = authUserRoleService.delete(userRole);
        AuthUserRole ul = authUserRoleService.queryById(userRole.getId());
        writeLog(result, String.format("从角色[%s]中删除用户[%s]", ul.getRoleName(), ul.getUserName()));
        return result;
    }
}
