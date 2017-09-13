package com.igalaxy.boot.controller.gd;

import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.gd.GdProduct;
import com.igalaxy.boot.domain.gd.GdType;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.service.gd.GdProductService;
import com.igalaxy.boot.service.gd.GdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fuguolei on 2017/2/3.
 */
@Controller
@RequestMapping("/gd/product")
public class GdProductController extends BaseController {

    @Autowired
    GdProductService gdProductService;

    @Autowired
    GdTypeService gdTypeService;

    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        List<GdType> list = gdTypeService.queryAllData(null);
        request.setAttribute("types", list);
        return "gd/product";
    }

    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult add(GdProduct t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult<GdType> result = service.save(t);
        return result;
    }

    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult edit(GdProduct t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult result = service.update(t);
        return result;
    }

    @RequestMapping(value = "/delete.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult delete(GdProduct t) {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        BaseResult result = service.delete(t);
        return result;
    }

    @Override
    protected BaseService getService() {
        return gdProductService;
    }


}
