package com.igalaxy.boot.conf;

import com.igalaxy.boot.domain.auth.AuthResource;
import com.igalaxy.boot.security.CollectionRedisSessionDao;
import com.igalaxy.boot.security.ShiroRealm;
import com.igalaxy.boot.service.auth.AuthResourceService;
import com.igalaxy.boot.service.auth.impl.AuthResourceServiceImpl;
import com.igalaxy.boot.util.StringUtils;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fuguolei on 2017/7/9.
 */
@Configuration
public class ShiroConfiguration {

    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

    @Bean(name = "authResourceService")
    public AuthResourceService getAuthResourceService() {
        return new AuthResourceServiceImpl();
    }

    @Bean(name = "shiroRealm")
    public ShiroRealm getShiroRealm() {
        return new ShiroRealm();
    }


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public CollectionRedisSessionDao getCollectionRedisSessionDao() {
        return new CollectionRedisSessionDao();
    }

    @Bean
    public SessionManager getSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(getCollectionRedisSessionDao());
        return sessionManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(getShiroRealm());
        dwsm.setSessionManager(getSessionManager());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(getDefaultWebSecurityManager());
        return aasa;
    }

    public LogoutFilter getLogoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/logout");
        return logoutFilter;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/auth/login");
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/unauthorized.html");
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("logout", getLogoutFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/auth/login", "anon");
        filterChainDefinitionMap.put("/auth/login.json", "anon");
        filterChainDefinitionMap.put("/usr/wechat/**", "anon");
        filterChainDefinitionMap.put("/pay/wechat-notify", "anon");
        filterChainDefinitionMap.put("/auth/logout.html", "logout");
        filterChainDefinitionMap.put("/**", "authc");
        List<AuthResource> resources = getAuthResourceService().getAll();
        filterChainDefinitionMap.put("/index.html", "perms[user:index]");
        for (AuthResource resource : resources) {
            if (StringUtils.isEmpty(resource.getUrlPattern())) {
                continue;
            }
            //此配置用于添加url过滤
            filterChainDefinitionMap.put(resource.getUrlPattern(), "perms[" + resource.getUrlPattern() + "]");
        }
        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}