package com.igalaxy.boot.controller.gd;

import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.gd.GdSKU;
import com.igalaxy.boot.domain.gd.GdType;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.service.gd.GdSKUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuguolei on 2017/2/3.
 */
@Controller
@RequestMapping("/gd/sku")
public class GdSKUController extends BaseController {

    @Autowired
    GdSKUService gdSKUService;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(Long productId, HttpServletRequest request) {
        request.setAttribute("productId", productId);
        return "gd/sku";
    }


    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult add(GdSKU t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult<GdType> result = service.save(t);
        return result;
    }

    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult edit(GdSKU t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult result = service.update(t);
        return result;
    }

    @RequestMapping(value = "/delete.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(GdSKU t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult result = service.delete(t);
        return result;
    }

    @Override
    public Object listData(Map<String, Object> params) throws Exception {
        HttpServletRequest request = getServletRequest();
        Map<String, Object> pageParams = new HashMap<>();
        pageParams.put("productId", request.getParameter("productId"));
        return super.listData(pageParams);
    }

    @Override
    protected BaseService getService() {
        return gdSKUService;
    }


}
