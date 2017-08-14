package com.igalaxy.boot.controller.admin.sys;

import com.igalaxy.boot.controller.admin.base.AdminController;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.sys.SysStgData;
import com.igalaxy.boot.enums.SysProperty;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.service.sys.SysStgDataService;
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
public class StgdataController extends AdminController {

    @Autowired
    SysStgDataService sysStgDataService;

    @Override
    protected BaseService getService() {
        return sysStgDataService;
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Object upload(@RequestParam("file") CommonsMultipartFile file, SysProperty.StgFilePrefixEnum prefix, Long bizId, HttpServletResponse response) {
        try {
            SysStgData sysStgData = sysStgDataService.upload(file, prefix, bizId);
            sysStgData = sysStgDataService.queryById(sysStgData.getId());
            return writeResult(response, new BaseResult(true, "成功", sysStgData));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return writeErrorResult(response, -1, "无此文件列别");
        } catch (Exception e) {
            e.printStackTrace();
            return writeErrorResult(response, -2, e.toString());
        }
    }
}
