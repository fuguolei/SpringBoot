package com.igalaxy.boot.conf;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.igalaxy.boot.domain.sys.SysResource;
import com.igalaxy.boot.security.CollectionRedisSessionDao;
import com.igalaxy.boot.security.ShiroRealm;
import com.igalaxy.boot.service.sys.SysResourceService;
import com.igalaxy.boot.service.sys.impl.SysResourceServiceImpl;
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
import java.util.*;

/**
 * Created by fuguolei on 2017/7/9.
 */
@Configuration
public class ShiroConfiguration {

    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

    @Bean(name = "sysResourceService")
    public SysResourceService getSysResourceService() {
        return new SysResourceServiceImpl();
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
        logoutFilter.setRedirectUrl("/admin");
        return logoutFilter;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean
                .setSecurityManager(getDefaultWebSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        shiroFilterFactoryBean.setSuccessUrl("/admin/index.html");
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/unauthorized.html");
        Map<String, Filter> filterMap = new HashMap<>();
//        CustomFormAuthenticationFilter filter = new CustomFormAuthenticationFilter();
//        filter.setLoginUrl("/admin/login");
//        filter.setPasswordParam("j_password");
//        filter.setUsernameParam("j_username");
//        filterMap.put("authc", filter);
        filterMap.put("logout", getLogoutFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        filterChainDefinitionMap.put("/admin/login.html", "anon");
        filterChainDefinitionMap.put("/admin/login.json", "anon");
        filterChainDefinitionMap.put("/admin/logout.html", "logout");
        filterChainDefinitionMap.put("/admin/**", "authc");
        List<SysResource> resources = getSysResourceService().getAll();
        filterChainDefinitionMap.put("/admin/index.html", "perms[user:index]");
        for (SysResource resource : resources) {
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

    @Bean
    public DefaultKaptcha captchaProducer() {
        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "125");
        properties.setProperty("kaptcha.image.height", "45");
        properties.setProperty("kaptcha.textproducer.font.size", "45");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        captchaProducer.setConfig(config);
        return captchaProducer;
    }


}