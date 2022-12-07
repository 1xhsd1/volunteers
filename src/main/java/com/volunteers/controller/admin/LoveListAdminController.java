package com.volunteers.controller.admin;

import com.alibaba.fastjson.JSON;
import com.volunteers.entity.Lovelist;
import com.volunteers.service.CommentService;
import com.volunteers.service.FavouriteService;
import com.volunteers.service.LovelistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class LoveListAdminController {
    @Resource
    private LovelistService loveListService;

    @Resource
    private FavouriteService favouLiteService;

    @Resource
    private CommentService commentService;

    /**
     * 发布爱心榜
     * @param loveList
     * @return
     */
    @ResponseBody
    @PostMapping("/lovelist/addLoveList")
    public String addLoveList(Lovelist loveList){
        Map<String,Object> map = new HashMap<>();
        try {
            Date releaseDate = new Date();
            loveList.setReleasseDate(releaseDate);
            //调用新增活动的方法
            int count = loveListService.addLoveList(loveList);
            if(count>0){
                map.put("success",true);//发布成功
            }else {
                map.put("success",false);//发布失败
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }


    /**
     *修改爱心榜
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/lovelist/update/{id}")
    public  String toUpdateBlog(@PathVariable("id")int id, Model model) throws Exception{
        System.out.println(id);
        model.addAttribute("id", id);
        return "/admin/updateLovelist";
    }

    /**
     * 处理前端请求，将数据渲染
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/lovelist/findFavouriteById")
    public Object findFavouriteById(Integer id){

        try {
            Lovelist lovelist = loveListService.findFavouriteById(id);

            return  lovelist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 接收数据并修改爱心榜
     * @param lovelist
     * @return
     */
    @ResponseBody
    @RequestMapping("/lovelist/updateLovelist")
    public String updateLoveList(Lovelist lovelist){
        Map<String, Object> map = new HashMap<>();
        try {
            Date releaseDate = new Date();
            lovelist.setReleasseDate(releaseDate);
            System.out.println(lovelist);

            //调用修改博客信息的方法
            int count = loveListService.updateLoveList(lovelist);
            if(count>0){//修改成功
                //保存修改成功的状态
                map.put("success",true);
            }else{
                //保存修改失败的状态
                map.put("success",false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }


}
