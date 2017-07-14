package com.igalaxy.boot.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fuguolei on 2017/7/3.
 */
public class HttpUtil {

    public static void writeJson(HttpServletResponse response, String result) {
        write(response, result, "application/json;charset=UTF-8");
    }

    public static void writeString(HttpServletResponse response, String result) {
        write(response, result, "text/html;charset=utf-8");
    }

    public static void write(HttpServletResponse response, String result, String contentType) {
        try {
            response.setContentType(contentType);
            response.getWriter().write(result);
            response.flushBuffer();
        } catch (IOException e) {

        }
    }


    /**
     * 是否是Ajax请求
     *
     * @param request
     * @return
     * @author SHANHY
     * @create 2017年4月4日
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest"))
            return true;

        String url = request.getRequestURI();
        if (url != null && url.endsWith("json"))
            return true;

        return false;

    }
}
