package com.igalaxy.boot.exception;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

/**
 * Created by fuguolei on 2017/7/14.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomErrorAttributes extends DefaultErrorAttributes
        implements ErrorAttributes, HandlerExceptionResolver, Ordered {

    private static final String ERROR_ATTRIBUTE = CustomErrorAttributes.class.getName()
            + ".ERROR";

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        storeErrorAttributes(request, ex);
        return null;
    }

    private void storeErrorAttributes(HttpServletRequest request, Exception ex) {
        request.setAttribute(ERROR_ATTRIBUTE, ex);
    }

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
                                                  boolean includeStackTrace) {
        Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
        errorAttributes.put("timestamp", new Date());
        errorAttributes.put("success", false);
        addErrorDetails(errorAttributes, requestAttributes, includeStackTrace);
        return errorAttributes;
    }

    private void addErrorDetails(Map<String, Object> errorAttributes,
                                 RequestAttributes requestAttributes, boolean includeStackTrace) {
        Throwable error = getError(requestAttributes);
        if (error != null) {

            while (error instanceof ServletException && error.getCause() != null) {
                error = ((ServletException) error).getCause();
            }

            //fuguolei
            //具体异常提示，目前只处理shiro的登录状态异常和权限异常
            if (error instanceof UnauthenticatedException) {
                errorAttributes.put("code", -1);
                errorAttributes.put("msg", "请登录");

                //json返回数据时，htto status 返回200
                requestAttributes.setAttribute("javax.servlet.error.status_code", 200, SCOPE_REQUEST);
                return;
            }
            if (error instanceof UnauthorizedException) {
                errorAttributes.put("code", -2);
                errorAttributes.put("msg", "您无此权限");

                //json返回数据时，htto status 返回200
                requestAttributes.setAttribute("javax.servlet.error.status_code", 200, SCOPE_REQUEST);
                return;
            }

            errorAttributes.put("exception", error.getClass().getName());
            addErrorMessage(errorAttributes, error);
            if (includeStackTrace) {
                addStackTrace(errorAttributes, error);
            }
        }
        Object message = getAttribute(requestAttributes, "javax.servlet.error.message");
        if ((!StringUtils.isEmpty(message) || errorAttributes.get("msg") == null)
                && !(error instanceof BindingResult)) {
            errorAttributes.put("msg",
                    StringUtils.isEmpty(message) ? "No message available" : message);
        }
    }

    private void addErrorMessage(Map<String, Object> errorAttributes, Throwable error) {
        BindingResult result = extractBindingResult(error);
        if (result == null) {
            errorAttributes.put("msg", error.getMessage());
            return;
        }
        if (result.getErrorCount() > 0) {
            errorAttributes.put("errors", result.getAllErrors());
            errorAttributes.put("msg",
                    "Validation failed for object='" + result.getObjectName()
                            + "'. Error count: " + result.getErrorCount());
        } else {
            errorAttributes.put("msg", "No errors");
        }
    }

    private BindingResult extractBindingResult(Throwable error) {
        if (error instanceof BindingResult) {
            return (BindingResult) error;
        }
        if (error instanceof MethodArgumentNotValidException) {
            return ((MethodArgumentNotValidException) error).getBindingResult();
        }
        return null;
    }

    private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        errorAttributes.put("trace", stackTrace.toString());
    }

    @Override
    public Throwable getError(RequestAttributes requestAttributes) {
        Throwable exception = getAttribute(requestAttributes, ERROR_ATTRIBUTE);
        if (exception == null) {
            exception = getAttribute(requestAttributes, "javax.servlet.error.exception");
        }
        return exception;
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, SCOPE_REQUEST);
    }

}

