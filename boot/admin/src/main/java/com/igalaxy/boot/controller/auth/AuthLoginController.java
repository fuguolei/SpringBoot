package com.igalaxy.boot.controller.auth;

import com.google.code.kaptcha.Constants;
import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.service.usr.UsrUserService;
import com.igalaxy.boot.util.SessionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fuguolei on 2017/9/7.
 */
@Controller
@RequestMapping("/auth")
public class AuthLoginController extends BaseController {

    @Autowired
    UsrUserService usrUserService;

    @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
    public String logout_jsp(HttpServletResponse response) throws IOException {
        return "admin/login";
    }

    @RequestMapping(value = "login", produces = "text/html")
    public String loginHtml() {
        return "auth/login";
    }

    @RequestMapping("login")
    public BaseResult login() {
        return BaseResult.badRequest("请重新登录");
    }

    @RequestMapping("login.json")
    @ResponseBody
    public BaseResult login(UsrUser usrUser, String captcha) {

        logger.debug("user:{}", usrUser);
        logger.debug("captcha:{}", captcha);
        Subject currentUser = SecurityUtils.getSubject();
        String sessionCaptcha = (String) currentUser.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        logger.debug("sessionCaptcha:{}", sessionCaptcha);
//        if (captcha == null || !captcha.equalsIgnoreCase(sessionCaptcha))
//            return BaseResult.badRequest(-1, "验证码错误");

        UsernamePasswordToken token = new UsernamePasswordToken(usrUser.getAccount(), usrUser.getPassword());
        try {
            if (!currentUser.isAuthenticated()) {//使用shiro来验证
                token.setRememberMe(true);
                currentUser.login(token);//验证角色和权限
                UsrUser user = usrUserService.getUserByAccount(usrUser.getAccount());
                SessionUtils.setUserId(user.getId());
            }
            return BaseResult.ok("登录成功");
        } catch (Exception e) {
            return BaseResult.badRequest(-2, "账号密码错误");
        }
    }
}
