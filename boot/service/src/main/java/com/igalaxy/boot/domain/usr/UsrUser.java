package com.igalaxy.boot.domain.usr;

import com.igalaxy.boot.domain.BaseDomain;
import com.igalaxy.boot.domain.auth.AuthRole;
import com.igalaxy.boot.enums.SysProperty.WhetherEnum;

import java.util.List;

/**
 * Created by fuguolei
 */
public class UsrUser extends BaseDomain {

    private static final long serialVersionUID = -427838794924380991L;

    private String head;
    private String name;
    private String account;
    private String password;
    private WhetherEnum repeatPassword;
    private String lastLoginIP;
    private Long defaultAddress;
    //
    private List<AuthRole> roles;

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

    public List<AuthRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AuthRole> roles) {
        this.roles = roles;
    }

    public WhetherEnum getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(WhetherEnum repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Long getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Long defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
