package com.volunteers.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    /**
     * 创建 ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean (@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //实现登录拦截
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/toAdminIndex", "authc");
        filterMap.put("/admin/toWriteBlog", "authc");
        filterMap.put("/admin/index", "authc");
        filterMap.put("/toCommentManage", "authc");
        filterMap.put("/favorites/toFavoritesManage","authc");
        filterMap.put("/mail/toMessageManage","authc");
        filterMap.put("/enroll/**","authc");

        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //设置登录的请求
        filterFactoryBean.setLoginUrl("/toLogin");

        // 设置安全管理器
        filterFactoryBean.setSecurityManager(securityManager);
        return filterFactoryBean;
    }


    /**
     * 创建 DefaultWebSecurityManager
     * @Qualifier 注解
     * @Bean 注解里的 name 指定放到spring容器中的名字， 若不写， 默认为方法名
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 这里要吧 userRealm 和 securityManager 关联
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建 Realm
     * @Bean 的作用： 将该方法返回的对象放入spring容器， 以便给上边的方法使用
     */
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}

