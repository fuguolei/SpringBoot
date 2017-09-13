package com.igalaxy.boot.controller.auth;

import com.igalaxy.boot.controller.BaseController;
import com.igalaxy.boot.domain.auth.SysStgData;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.enums.SysProperty;
import com.igalaxy.boot.service.auth.SysStgDataService;
import com.igalaxy.boot.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by fuguolei
 */
@Controller
@RequestMapping("/sys/stgdata")
public class StgdataController extends BaseController {

    @Autowired
    SysStgDataService sysStgDataService;

    @Override
    protected BaseService getService() {
        return sysStgDataService;
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public BaseResult upload(@RequestParam("file") CommonsMultipartFile file, SysProperty.StgFilePrefixEnum prefix, Long bizId, HttpServletResponse response) {
        try {
            SysStgData sysStgData = sysStgDataService.upload(file, prefix, bizId);
            sysStgData = sysStgDataService.queryById(sysStgData.getId());
            return BaseResult.ok(sysStgData);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return BaseResult.badRequest(-1, "无此文件列别");
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.badRequest(-2, e.toString());
        }
    }
}
