package com.igalaxy.boot.controller.web.usr;

import com.igalaxy.boot.controller.web.WeChatController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.usr.UsrAddress;
import com.igalaxy.boot.service.usr.UsrAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        return "web/usr/address/list";
    }

    @RequestMapping("add.json")
    public String list(UsrAddress usrAddress, HttpServletResponse response) {
        usrAddress.setUserId(getUserId());
        BaseResult result = usrAddressService.save(usrAddress);
        return writeResult(response, result);
    }

}
