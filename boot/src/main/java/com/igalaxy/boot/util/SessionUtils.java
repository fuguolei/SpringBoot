package com.igalaxy.boot.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by fuguolei  on 2017/7/8.
 */
public final class SessionUtils {
    private final static String CACHE_USER_ID = "user_id";

    private final static String CACHE_REDIRECT = "redirect";

    public static String getRedirect() {
        return (String) getCache(CACHE_REDIRECT);

    }

    public static void setRedirect(String redirect) {
        setCache(CACHE_REDIRECT, redirect);
    }

    public static Long getUserId() {
        return (Long) getCache(CACHE_USER_ID);
    }

    public static void setUserId(Long sysUserId) {
        setCache(CACHE_USER_ID, sysUserId);
    }

    private static Object getCache(String key) {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            return session.getAttribute(key);
        } catch (Exception e) {

        }
        return null;
    }

    private static void setCache(String key, Object value) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(key, value);
    }

}
