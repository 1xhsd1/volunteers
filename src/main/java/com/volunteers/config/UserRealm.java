package com.volunteers.config;

import com.volunteers.entity.User;
import com.volunteers.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


import javax.annotation.Resource;

/**
 * 用户自定义realm
 */
public class UserRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    /**
     * 验证权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 验证身份（登录）
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            //得到用户名
            String username = (String) token.getPrincipal();
            //调用自定义方法
            User user = userService.findUserByUsername(username);
            if (user!=null){
                //创建验证身份对象
                SimpleAuthenticationInfo realm = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "UserRealm");
                //登录成功
                return realm;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //登录失败
        return null;
    }
}
