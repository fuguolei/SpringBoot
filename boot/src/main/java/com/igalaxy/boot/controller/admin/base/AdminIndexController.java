package com.igalaxy.boot.controller.admin.base;

import com.google.code.kaptcha.Constants;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.MenuJson;
import com.igalaxy.boot.domain.sys.SysResource;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.service.sys.SysResourceService;
import com.igalaxy.boot.service.usr.UsrUserService;
import com.igalaxy.boot.util.SessionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei on 2017/1/30.
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController extends AdminController {

    @Resource
    private SysResourceService sysResourceService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "redirect:/admin/index.html";
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView index_jsp(HttpServletResponse response) throws IOException {
        List<SysResource> resources = sysResourceService.selectAllResourceByUserId(getUserId());
        List<MenuJson> menuJsons = MenuJson.convertMemuList(resources);
        Map<String, Object> params = new HashedMap();
        params.put("menus", menuJsons);
        params.put("currentUser", usrUserService.queryById(getUserId()));
        writeLog("进入系统");
        return new ModelAndView("admin/index", params);
    }

    @RequestMapping(value = "/logout.html", method = RequestMethod.GET)
    public String logout_jsp(HttpServletResponse response) throws IOException {
        return "admin/login";
    }


    @Autowired
    UsrUserService usrUserService;

    @RequestMapping(value = "login", produces = "text/html")
    public String loginHtml() {
        return "admin/login";
    }

    @RequestMapping("login")
    public String login(HttpServletResponse response) {
        return writeResult(response, new BaseResult(false, -1, "请重新登录"));
    }

    @RequestMapping(value = "login.json", method = RequestMethod.POST)
    public String login(UsrUser usrUser, String captcha, HttpServletResponse response) throws ServletException, IOException {

        logger.debug("user:{}", usrUser);
        logger.debug("captcha:{}", captcha);
        Subject currentUser = SecurityUtils.getSubject();
        String sessionCaptcha = (String) currentUser.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        logger.debug("sessionCaptcha:{}", sessionCaptcha);
        if (!sessionCaptcha.equalsIgnoreCase(captcha))
            return writeErrorResult(response, -2, "验证码错误");

        UsernamePasswordToken token = new UsernamePasswordToken(usrUser.getAccount(), usrUser.getPassword());
        try {
            if (!currentUser.isAuthenticated()) {//使用shiro来验证
                token.setRememberMe(true);
                currentUser.login(token);//验证角色和权限
                UsrUser user = usrUserService.getUserByAccount(usrUser.getAccount());
                SessionUtils.setUserId(user.getId());
            }
            return writeSuccessResult(response, "登录成功");
        } catch (Exception e) {
            return writeErrorResult(response, -1, "账号密码错误");
        }
    }

}
