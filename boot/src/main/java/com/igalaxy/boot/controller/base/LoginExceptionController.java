package com.igalaxy.boot.controller.base;

import com.igalaxy.boot.domain.dto.BaseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by fuguolei on 2017/7/13.
 */
@Controller
@RequestMapping("admin/login")
public class LoginExceptionController extends BaseController {

    @RequestMapping(produces = "text/html")
    public String loginHtml() {
        return "admin/login";
    }

    @RequestMapping
    public String login(HttpServletResponse response) {
        return writeResult(response, new BaseResult(false, -1, "请重新登录"));
    }
}