package com.example.config;

import com.example.pojo.User;
import com.example.service.UserService;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

// 自定义的 UserRealm   extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权 doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");

        // 拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        // 拿到 User 对象，在认证中 SimpleAuthenticationInfo 的第一个参数传入的
        User currentUser = (User) subject.getPrincipal();

        // 设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());

        return info;
    }
    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证 doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        // 连接真实的数据库
        User user = userService.queryUserByName(userToken.getUsername());

        if (user == null){
            return null;    // 抛出异常， UnknownAccountException
        }

        // 密码认证， shiro 本身去做
        return new SimpleAuthenticationInfo(user, user.getPwd(),"");
    }
}
