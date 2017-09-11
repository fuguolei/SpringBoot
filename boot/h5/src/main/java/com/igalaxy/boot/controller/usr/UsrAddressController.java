package com.igalaxy.boot.controller.usr;

import com.igalaxy.boot.controller.WeChatController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.usr.UsrAddress;
import com.igalaxy.boot.service.usr.UsrAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fuguolei on 2017/8/5.
 */
@Controller
@RequestMapping("/usr/address")
public class UsrAddressController extends WeChatController {

    @Autowired
    UsrAddressService usrAddressService;

    @RequestMapping("list.html")
    public String list(HttpServletRequest request) {
        List<UsrAddress> list = usrAddressService.queryMyList();
        request.setAttribute("list", list);
        return "usr/address/list";
    }

    @RequestMapping("add.json")
    @ResponseBody
    public BaseResult list(UsrAddress usrAddress) {
        usrAddress.setUserId(getUserId());
        BaseResult result = usrAddressService.save(usrAddress);
        return result;
    }

}
