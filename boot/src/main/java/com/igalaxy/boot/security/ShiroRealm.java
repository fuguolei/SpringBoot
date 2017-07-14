package com.igalaxy.boot.security;

import com.alibaba.druid.util.StringUtils;
import com.igalaxy.boot.domain.sys.SysPermission;
import com.igalaxy.boot.domain.sys.SysResource;
import com.igalaxy.boot.domain.sys.SysRole;
import com.igalaxy.boot.domain.sys.SysUser;
import com.igalaxy.boot.service.sys.SysUserService;
import com.igalaxy.boot.util.CommonUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fuguolei on 2017/7/9.
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    SysUserService sysUserService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String id = (String) token.getPrincipal();
        SysUser user = sysUserService.getUserByAccount(id);
        if (user == null) {
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user.getAccount(),
                user.getPassword(),
                getName()
        );
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        SysUser user = CommonUtils.getCurrentUser();
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<SysRole> uroles = user.getRoles();
        Set<String> perms = new HashSet<String>();
        perms.add("user:index");
        for (SysRole role : uroles) {
            List<SysResource> resources = role.getResources();
            for (SysResource resource : resources) {
                String permission = resource.getUrlPattern();
                if (StringUtils.isEmpty(permission)) {
                    continue;
                }
                perms.add(permission);
            }

            List<SysPermission> permissions = role.getPermissions();
            for (SysPermission p : permissions) {
                String permission = p.getPermission();
                if (StringUtils.isEmpty(permission)) {
                    continue;
                }
                perms.add(permission);
            }
        }
        authorizationInfo.setStringPermissions(perms);

        return authorizationInfo;
    }
}
