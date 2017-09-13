package com.igalaxy.boot.security;

import com.alibaba.druid.util.StringUtils;
import com.igalaxy.boot.domain.auth.AuthPermission;
import com.igalaxy.boot.domain.auth.AuthResource;
import com.igalaxy.boot.domain.auth.AuthRole;
import com.igalaxy.boot.domain.usr.UsrUser;
import com.igalaxy.boot.service.usr.UsrUserService;
import com.igalaxy.boot.util.SessionUtils;
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
    UsrUserService usrUserService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String id = (String) token.getPrincipal();
        UsrUser user = usrUserService.getUserByAccount(id);
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
        Long userId = SessionUtils.getUserId();
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<AuthRole> uroles = usrUserService.queryById(userId).getRoles();
        Set<String> perms = new HashSet<String>();
        perms.add("user:index");
        for (AuthRole role : uroles) {
            List<AuthResource> resources = role.getResources();
            for (AuthResource resource : resources) {
                String permission = resource.getUrlPattern();
                if (StringUtils.isEmpty(permission)) {
                    continue;
                }
                perms.add(permission);
            }

            List<AuthPermission> permissions = role.getPermissions();
            for (AuthPermission p : permissions) {
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
