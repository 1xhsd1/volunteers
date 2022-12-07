package com.volunteers.controller;


import com.volunteers.entity.Favourite;
import com.volunteers.service.FavouriteService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
@RequestMapping("/favourite")
public class FavouriteController {  
    @Resource
    private FavouriteService favouriteService;

    /**
     * 加入收藏业务
     * @param eventId
     * @param model
     * @return
     * @throws Exception
     */
    @RequiresAuthentication
    @GetMapping("/addFavourite/{eventId}")
//    @ResponseBody
    public String addFavorites(@PathVariable("eventId") int eventId, Model model, HttpSession session)throws Exception{
        Favourite favorites = new Favourite();
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        //设置评论信息
        try {
            if (subject.isAuthenticated()) {
                List<Favourite> favorites1 = favouriteService.queryByName(principal, eventId);
                if (favorites1.size()>0){
//                    model.addAttribute("addInfo","已加入收藏");
                    session.setAttribute("addInfo", "已加入收藏");

                }else {
                    favorites.setEventId(eventId);
                    favorites.setAddDate(new Date());


                    favorites.setUsername(principal);
                    //调用MP内置方法保存
                    favouriteService.save(favorites);

                    session.setAttribute("addInfo", "收藏成功");

                }


            }else {
                return "admin/login";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //评论成功后返回原页面
        return "redirect:/event/view/"+eventId   ;
    }

    /**
     * 根据用户名获取收藏列表
     * @return
     */
    @RequestMapping("/toFavouriteManage")
    private String favoritesManage(Model model) throws Exception{
        //获取用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        //获取评论列表
        List<Favourite> favorites = favouriteService.queryList(username);
        model.addAttribute("favourite",favorites);
        return "admin/favoritesManage";
    }

    /**
     * 取消收藏操作
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/delete/{id}")
    private String deleteFavorites(@PathVariable("id")int id) throws Exception{
        favouriteService.deleteFavoritesById(id);
        return "redirect:/favourite/toFavouriteManage";

    }
}

