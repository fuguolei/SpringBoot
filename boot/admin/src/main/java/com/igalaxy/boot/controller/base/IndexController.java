package com.igalaxy.boot.controller.base;

import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.domain.auth.AuthResource;
import com.igalaxy.boot.domain.dto.MenuJson;
import com.igalaxy.boot.service.auth.AuthResourceService;
import com.igalaxy.boot.service.usr.UsrUserService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei on 2017/1/30.
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Resource
    private AuthResourceService authResourceService;
    @Autowired
    UsrUserService usrUserService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "redirect:/index.html";
    }

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView index_jsp(HttpServletResponse response) throws IOException {
        List<AuthResource> resources = authResourceService.selectAllResourceByUserId(getUserId());
        List<MenuJson> menuJsons = MenuJson.convertMemuList(resources);
        Map<String, Object> params = new HashedMap();
        params.put("menus", menuJsons);
        params.put("admin", usrUserService.queryById(getUserId()));
        writeLog("进入系统");
        return new ModelAndView("index", params);
    }

}
