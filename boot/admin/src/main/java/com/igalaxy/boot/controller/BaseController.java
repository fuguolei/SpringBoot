package com.igalaxy.boot.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.PageData;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.util.DateUtils;
import com.igalaxy.boot.util.SessionUtils;
import com.igalaxy.boot.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuguolei on 2017/8/14.
 */
public class BaseController {


    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Long getUserId() {
        return SessionUtils.getUserId();
    }

    protected HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpServletResponse getServletResponse() {
        HttpServletResponse resp = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
        return resp;
    }

    protected void writeLog(Object... log) {

    }

    @RequestMapping(value = "list.json", method = RequestMethod.POST)
    @ResponseBody
    public Object listData(Map<String, Object> params) throws Exception {
        BaseService service = getService();
        if (service == null)
            return BaseResult.badRequest("不支持此接口");
        PageData page = service.queryPage(getPageParams(params));
        return BaseResult.ok(page);
    }

    static Gson gson = new Gson();

    protected Map<String, Object> getPageParams(Map<String, Object> params) {
        HttpServletRequest request = getServletRequest();
        Map<String, Object> pageParams = new HashMap<>();
        if (request.getParameter("pageNumber") != null) {
            pageParams.put("pageSize", Integer.parseInt(request.getParameter("pageSize")));
            pageParams.put("pageNo", Integer.parseInt(request.getParameter("pageNumber")));
        }
        String searchStr = request.getParameter("search");
        if (StringUtils.isNotEmpty(searchStr)) {
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            Map<String, String> myMap = gson.fromJson(searchStr, type);
            for (String key : myMap.keySet()) {
                String value = myMap.get(key);
                if (StringUtils.isNotEmpty(value)) {
                    if (key.startsWith("date")) {
                        pageParams.put(key, DateUtils.getDate(value));
                    } else {
                        pageParams.put(key, value);
                    }
                    System.out.println(key + ":" + value);
                }
            }
        }
        if (params != null) {
            pageParams.putAll(params);
        }
        Map<String, Object> otherParams = getOtherParams();
        if (otherParams != null) {
            pageParams.putAll(otherParams);
        }
        initPageParams(pageParams);
        return pageParams;
    }

    protected void initPageParams(Map<String, Object> params) {

    }

    protected Map<String, Object> getOtherParams() {
        return null;
    }

    protected BaseService getService() {
        return null;
    }

}
