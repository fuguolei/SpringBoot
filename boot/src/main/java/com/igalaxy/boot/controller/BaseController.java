package com.igalaxy.boot.controller;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.util.HttpUtil;
import com.igalaxy.boot.util.SessionUtils;
import com.igalaxy.boot.util.json.AppJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    protected String writeResult(HttpServletResponse response, BaseResult result, String[] field) {
        HttpUtil.writeJson(response, AppJsonUtils.toJson(result, field));
        return null;
    }

    protected String writeResultOld(HttpServletResponse response, Object data) {
        HttpUtil.writeJson(response, AppJsonUtils.toJsonOld(data));
        return null;
    }

    protected void writeLog(Object... log) {

    }
}
