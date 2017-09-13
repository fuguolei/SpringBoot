package com.igalaxy.boot.security;

import com.igalaxy.boot.util.SessionUtils;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by fuguolei on 2017/8/11.
 */
public class WeChatFilter extends UserFilter {
    public WeChatFilter() {
        setLoginUrl("/usr/wechat/index.html");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean isAllowed = super.isAccessAllowed(request, response, mappedValue);
        if (isAllowed) {
            SessionUtils.setRedirect(((HttpServletRequest) request).getRequestURI());
        }
        return isAllowed;
    }
}
