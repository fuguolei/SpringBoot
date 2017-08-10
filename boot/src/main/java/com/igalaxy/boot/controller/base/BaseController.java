package com.igalaxy.boot.controller.base;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.domain.dto.ListViewPage;
import com.igalaxy.boot.service.base.BaseService;
import com.igalaxy.boot.util.DateUtils;
import com.igalaxy.boot.util.HttpUtil;
import com.igalaxy.boot.util.SessionUtils;
import com.igalaxy.boot.util.StringUtils;
import com.igalaxy.boot.util.json.AppJsonUtils;
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
 * Created by fuguolei on 2017/7/8.
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

    @RequestMapping(value = "list.json", method = RequestMethod.POST)
    @ResponseBody
    public Object data(HttpServletResponse response) throws Exception {
        BaseService service = getService();
        if (service == null)
            return null;
        ListViewPage page = service.queryPageList(getPageParams());
        return writeResultOld(response, page);
    }

    static Gson gson = new Gson();

    protected Map<String, Object> getPageParams() {
        HttpServletRequest request = getServletRequest();
        Map<String, Object> pageParams = new HashMap<>();
        if (request.getParameter("rows") != null) {
            pageParams.put("pageSize", Integer.parseInt(request.getParameter("rows")));
            pageParams.put("pageNo", Integer.parseInt(request.getParameter("page")));
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

    protected String writeErrorResult(HttpServletResponse response, int code, String msg) {
        BaseResult result = new BaseResult(false, code, msg);
        HttpUtil.writeJson(response, AppJsonUtils.toJson(result, null));
        return null;
    }

    protected String writeSuccessResult(HttpServletResponse response, String msg) {
        BaseResult result = new BaseResult(true, 0, msg);
        HttpUtil.writeJson(response, AppJsonUtils.toJson(result, null));
        return null;
    }

    protected String writeSuccessResult(HttpServletResponse response, BaseDomain data) {
        BaseResult result = new BaseResult(true, data);
        HttpUtil.writeJson(response, AppJsonUtils.toJson(result, null));
        return null;
    }

    protected String writeResult(HttpServletResponse response, BaseResult result) {
        HttpUtil.writeJson(response, AppJsonUtils.toJson(result, null));
        return null;
    }

    protected String writeResult(HttpServletResponse response, BaseResult result, String[] filed) {
        HttpUtil.writeJson(response, AppJsonUtils.toJson(result, filed));
        return null;
    }

    protected String writeResultOld(HttpServletResponse response, Object data) {
        HttpUtil.writeJson(response, AppJsonUtils.toJsonOld(data));
        return null;
    }

    protected void writeLog(Object... log) {

    }
}
