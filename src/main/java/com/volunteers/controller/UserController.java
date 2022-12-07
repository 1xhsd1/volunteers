package com.volunteers.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.volunteers.entity.Event;
import com.volunteers.entity.User;
import com.volunteers.service.EventService;
import com.volunteers.service.UserService;
import com.volunteers.util.SysConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session) throws Exception{
        //得到当前登录用户
        Subject subject = SecurityUtils.getSubject();
        //创建登录令牌
        try{
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            //登录
            subject.login(token);
            //保存当前登录用户
            subject.getSession().setAttribute(SysConstant.LOGINUSER, user);

            //获取完整用户
            User user1 = userService.findUserByUsername(user.getUsername());
            session.setAttribute("user", user1);

            model.addAttribute("username", user.getUsername());
            return "redirect:/";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            model.addAttribute("msg", "用户名不正确！");
            return "/admin/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误！");
            return "/admin/login";
        }

    }

    /**
     * 注册新用户
     * @param username
     * @param password
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/registy")
    public String registy(@RequestParam("username")String username,
                          @RequestParam("password")String password,
                          Model model)throws Exception{
        List list = userService.queryBloggerList(username);
        if (list.size()>0){
            model.addAttribute("flag",1);
        }else {
            userService.registy(username, password);
            model.addAttribute("flag",2);

        }
        return "admin/registy";
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }

}

