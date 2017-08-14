package com.igalaxy.boot.controller.admin.sys;

import com.igalaxy.boot.controller.admin.base.AdminController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.service.usr.UsrUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jinlong on 2017/2/2.
 */
@Controller
@RequestMapping("/admin/sys/password")
public class SysPassowrdController extends AdminController {

    @Resource
    UsrUserService usrUserService;

    @RequestMapping("/index.html")
    public String resetPassword() {
        writeLog("进入密码修改页面");
        return "sys/resetPassword";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public Object resetPassword(String oldPassword, String newPassword, HttpServletResponse response) {
        BaseResult result = usrUserService.resetMyPassword(oldPassword, newPassword);
        writeLog(result, "重置密码");
        return writeResult(response, result);
    }
}