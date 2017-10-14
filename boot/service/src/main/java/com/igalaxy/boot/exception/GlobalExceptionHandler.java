package com.igalaxy.boot.exception;

import com.igalaxy.boot.domain.dto.BaseResult;
import com.igalaxy.boot.util.HttpUtil;
import com.igalaxy.boot.util.json.AppJsonUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fuguolei on 2017/8/1.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = UnauthorizedException.class)
    public ModelAndView unauthorizedExceptionHandler(HttpServletRequest request, HttpServletResponse response, UnauthorizedException e) throws Exception {
        String uri = request.getRequestURI();
        String accept = request.getHeader("accept");
        if (uri.endsWith(".json") || (accept != null && accept.contains("json"))) {
            HttpUtil.writeJson(response, AppJsonUtils.toJsonOld(BaseResult.badRequest(-10, "无此权限")));
            return null;
        }
        ModelAndView view = new ModelAndView("401");
        view.setStatus(HttpStatus.UNAUTHORIZED);
        return view;
    }
}
