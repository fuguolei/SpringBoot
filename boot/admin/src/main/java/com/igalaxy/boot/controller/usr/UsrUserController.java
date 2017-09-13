package com.igalaxy.boot.controller.usr;

import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.enums.SysProperty.WhetherEnum;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.service.usr.UsrUserService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by fuguolei on 2017/2/6.
 */
@Controller
@RequestMapping("/usr/user")
public class UsrUserController extends BaseController {

    @Resource
    UsrUserService usrUserService;

    @Override
    protected BaseService getService() {
        return usrUserService;
    }

    @Override
    protected Map<String, Object> getOtherParams() {
        Map<String, Object> params = new HashedMap();
        params.put("userId", getUserId());
        return params;
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index() {
        writeLog("进入用户管理页面");
        return "usr/user";
    }

    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult add(UsrUser usrUser, HttpServletResponse response) {
        if (usrUser.getAccount().indexOf(" ") >= 0)
            return BaseResult.badRequest(-1, "账号包含空格");
        UsrUser exUser = usrUserService.getUserByAccount(usrUser.getAccount());
        if (exUser != null) {
            return BaseResult.badRequest(-2, "账号重复");
        }
        usrUser.setRepeatPassword(WhetherEnum.No);
        BaseResult result = usrUserService.save(usrUser);
        writeLog(result, String.format("添加用户[%s]", usrUser.getName()));
        return result;
    }

    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult edit(UsrUser usrUser, HttpServletResponse response) {
        BaseResult result = usrUserService.update(usrUser);
        writeLog(result, String.format("更新用户[%s]", usrUser.getName()));
        return result;
    }

    @RequestMapping(value = "/delete.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(UsrUser usrUser, HttpServletResponse response) {
        BaseResult result = usrUserService.delete(usrUser);
        writeLog(result, String.format("删除用户[%s]", usrUser.getId()));
        return result;
    }

    /**
     * 未用到 fuguolei
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult resetPassword(UsrUser usrUser, HttpServletResponse response) {
        usrUser.setPassword("12345678");
        usrUser.setRepeatPassword(WhetherEnum.Yes);
        UsrUser user = usrUserService.queryById(usrUser.getId());
        BaseResult result = usrUserService.setPassword(usrUser);
        writeLog(result, String.format("重置用户[%s]密码", user.getName()));
        return result;
    }
}
