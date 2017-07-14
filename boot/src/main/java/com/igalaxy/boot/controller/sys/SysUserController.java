package com.igalaxy.boot.controller.sys;

import com.igalaxy.boot.controller.base.BaseController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.sys.SysUser;
import com.igalaxy.boot.enums.SysProperty.WhetherEnum;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.service.sys.SysUserService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by jinlong on 2017/2/6.
 */
@Controller
@RequestMapping("/admin/sys/user")
public class SysUserController extends BaseController {

    @Resource
    SysUserService sysUserService;

    @Override
    protected BaseService getService() {
        return sysUserService;
    }

    @Override
    protected Map<String, Object> getOtherParams() {
        Map<String, Object> params = new HashedMap();
        params.put("userId", getUserId());
        return params;
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index_jsp() {
        writeLog("进入用户管理页面");
        return "sys/user";
    }

    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    public String addUser(SysUser sysUser, HttpServletResponse response) {
        if (sysUser.getAccount().indexOf(" ") >= 0)
            return writeResult(response, new BaseResult(false, "账号包含空格"));
        SysUser exUser = sysUserService.getUserByAccount(sysUser.getAccount());
        if (exUser != null) {
            return writeResult(response, new BaseResult(false, "账号重复"));
        }
//        sysUser.setPassword("12345678");
        sysUser.setRepeatPassword(WhetherEnum.No);
        BaseResult result = sysUserService.save(sysUser);
        writeLog(result, String.format("添加用户[%s]", sysUser.getName()));
        return writeResult(response, result);
    }

    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    public String updateUser(SysUser sysUser, HttpServletResponse response) {
        BaseResult result = sysUserService.update(sysUser);
        writeLog(result, String.format("更新用户[%s]", sysUser.getName()));
        return writeResult(response, result);
    }

    @RequestMapping(value = "/delete.json", method = RequestMethod.POST)
    public String deleteUser(SysUser sysUser, HttpServletResponse response) {
        BaseResult result = sysUserService.delete(sysUser);
        writeLog(result, String.format("删除用户[%s]", sysUser.getId()));
        return writeResult(response, result);
    }

    /**
     * 未用到 fuguolei
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPassword(SysUser sysUser, HttpServletResponse response) {
        sysUser.setPassword("12345678");
        sysUser.setRepeatPassword(WhetherEnum.Yes);
        SysUser user = sysUserService.queryById(sysUser.getId());
        BaseResult result = sysUserService.setPassword(sysUser);
        writeLog(result, String.format("重置用户[%s]密码", user.getName()));
        return writeResult(response, result);
    }
}
