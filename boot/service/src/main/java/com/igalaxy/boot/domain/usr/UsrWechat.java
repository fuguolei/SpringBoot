package com.igalaxy.boot.domain.usr;

import com.igalaxy.boot.domain.BaseDomain;

/**
 * Created by fuguolei
 */
public class UsrWechat extends BaseDomain {

    private static final long serialVersionUID = -427838794924380991L;
    
    private Long userId;
    private String openId;
    private String userInfo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
