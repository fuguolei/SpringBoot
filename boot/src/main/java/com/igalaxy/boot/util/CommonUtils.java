package com.igalaxy.boot.util;

import com.igalaxy.boot.domain.sys.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by fuguolei  on 2017/7/8.
 */
public final class CommonUtils {
    private final static String Cache_User_Key = "currentUser";

    public static SysUser getCurrentUser() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            Object o = session.getAttribute(Cache_User_Key);
            if (o != null) {
                return (SysUser) o;
            }
        } catch (Exception e) {

        }
        return null;

    }

    public static void setCurrentUser(SysUser sysUser) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(Cache_User_Key, sysUser);
    }


}
