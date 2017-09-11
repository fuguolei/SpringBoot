package com.igalaxy.boot.controller.usr;

import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.service.usr.UsrUserService;
import com.igalaxy.boot.service.usr.UsrWechatService;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fuguolei on 2017/8/5.
 */
@Controller
@RequestMapping("/usr/wechat")
public class UsrWeChatController extends BaseController {

    static final String HOST = "qg.igalaxy.com.cn";
    static final String APP_ID = "wx855e27a3d08ed53f";
    @Autowired
    UsrUserService usrUserService;

    @Autowired
    UsrWechatService usrWechatService;

    @RequestMapping("/index.html")
    public String index_jsp(String url, HttpServletResponse response) {
        String location = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID + "&redirect_uri=http%3A%2F%2F" + HOST + "%2Fusr%2Fwechat%2Freturn.html&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        response.addHeader("location", location);
        response.setStatus(302);
        return null;
    }

    @RequestMapping("return.html")
    public String returnHtml(HttpServletRequest request, String code, HttpServletResponse response) {
        if (code != null && code.length() > 0) {
            BaseResult result = usrWechatService.oauth(code);
            if (result.isSuccess())
                try {
                    WebUtils.redirectToSavedRequest(request, response, "/");
                } catch (IOException e) {
                    e.printStackTrace();
                    return "/";
                }
        }
        return null;
    }
}
