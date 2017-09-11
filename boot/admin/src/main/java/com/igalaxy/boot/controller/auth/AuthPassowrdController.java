package com.igalaxy.boot.controller.auth;

import com.igalaxy.boot.controller.base.AdminController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.service.usr.UsrUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fuguolei on 2017/2/2.
 */
@Controller
@RequestMapping("/auth/password")
public class AuthPassowrdController extends AdminController {

    @Resource
    UsrUserService usrUserService;

    @RequestMapping("/index.html")
    public String resetPassword() {
        writeLog("进入密码修改页面");
        return "auth/resetPassword";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult resetPassword(String oldPassword, String newPassword, HttpServletResponse response) {
        BaseResult result = usrUserService.resetMyPassword(oldPassword, newPassword);
        writeLog(result, "重置密码");
        return result;
    }
}
