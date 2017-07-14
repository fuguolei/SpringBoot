package com.igalaxy.boot.controller.base;

import com.igalaxy.boot.exception.CustomErrorAttributes;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by fuguolei on 2017/7/13.
 */
//@Controller
//@RequestMapping("${server.error.path:${error.path:/error}}")
public class ExceptionController /* extends  BasicErrorController */{
//    public ExceptionController() {
//        super(new CustomErrorAttributes(), new ErrorProperties());
//    }
//
//    //fuguolei重写此接口，所有错误返回200
//    @Override
//    protected HttpStatus getStatus(HttpServletRequest request) {
//        return HttpStatus.OK;
//    }
}
