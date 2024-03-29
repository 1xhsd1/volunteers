package com.volunteers.controller;


import com.volunteers.entity.Lovelist;
import com.volunteers.service.LovelistService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.jws.WebParam;
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
@RequestMapping("/lovelist")
public class LovelistController {
    @Resource
    private LovelistService lovelistService;

    /**
     * 查询员工榜单
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("toLoveList")
    public String toLoveList(Model model) throws Exception{
        List<Lovelist> loveList = lovelistService.findLoveList("");
        model.addAttribute("loveList", loveList);
        return "foreign/loveList";
    }

    @RequestMapping("/{id}")
    public String favouriteDetail(@PathVariable Integer id, Model model){
        try {
            Lovelist favourite = lovelistService.findFavouriteById(id);
            model.addAttribute("favourite",favourite);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "foreign/favouriteDetail";

    }

    /**
     * 员工榜管理
     * @return
     */
    @RequestMapping("/toLoveListManage")
    public String toLoveListManage(Model model){

        Subject subject = SecurityUtils.getSubject();
        String userName = (String) subject.getPrincipal();
        try {
            List<Lovelist> loveList = lovelistService.findLoveList(userName);
            model.addAttribute("loveList", loveList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/loveListManage";
    }

    /**
     * 根据id删除员工榜
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deleteLoveList(@PathVariable Integer id){
        try {
            lovelistService.deleteLoveList(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/lovelist/toLoveListManage";
    }
}

