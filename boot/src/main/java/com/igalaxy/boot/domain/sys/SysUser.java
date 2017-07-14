package com.igalaxy.boot.domain.sys;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.enums.SysProperty.WhetherEnum;

import java.util.List;

/**
 * Created by fuguolei
 */
public class SysUser extends BaseDomain {
    private String name;
    private String account;
    private String password;
    private WhetherEnum repeatPassword;
    private String lastLoginIP;
    //
    private List<SysRole> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public WhetherEnum getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(WhetherEnum repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
