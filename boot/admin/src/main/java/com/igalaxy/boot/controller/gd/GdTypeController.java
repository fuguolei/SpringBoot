package com.igalaxy.boot.controller.gd;

import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.gd.GdType;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.service.gd.GdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fuguolei on 2017/2/3.
 */
@Controller
@RequestMapping("/gd/type")
public class GdTypeController extends BaseController {

    @Autowired
    GdTypeService gdTypeService;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index() {
        return "gd/type";
    }


    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult add(GdType t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult<GdType> result = service.save(t);
        return result;
    }

    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult edit(GdType t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult result = service.update(t);
        return result;
    }

    @RequestMapping(value = "/delete.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(GdType t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult result = service.delete(t);
        return result;
    }

    @Override
    protected BaseService getService() {
        return gdTypeService;
    }


}
