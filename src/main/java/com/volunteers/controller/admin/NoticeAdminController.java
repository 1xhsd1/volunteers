package com.volunteers.controller.admin;

import com.alibaba.fastjson.JSON;
import com.volunteers.entity.Lovelist;
import com.volunteers.entity.Notice;
import com.volunteers.service.CommentService;
import com.volunteers.service.FavouriteService;
import com.volunteers.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class NoticeAdminController {
    @Resource
    private NoticeService noticeService;

    @Resource
    private FavouriteService favouLiteService;

    @Resource
    private CommentService commentService;

    /**
     * 发布社区公告
     * @param notice
     * @return
     */
    @ResponseBody
    @PostMapping("/notice/addNotice")
    public String addNotice(Notice notice){
        Map<String,Object> map = new HashMap<>();
        try {
            Date releaseDate = new Date();
            notice.setReleaseDate(releaseDate);
            //调用新增活动的方法
            int count = noticeService.addNotice(notice);
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
     *修改社区公告
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/notice/update/{id}")
    public  String toUpdateBlog(@PathVariable("id")int id, Model model) throws Exception{
        model.addAttribute("id", id);
        return "/admin/updateNotice";
    }

    /**
     * 处理前端请求，将数据渲染
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/notice/findFavouriteById")
    public Object findFavouriteById(Integer id){

        try {
            Notice notice = noticeService.findNoticeById(id);

            return  notice;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 接收数据并修改公告
     * @param notice
     * @return
     */
    @ResponseBody
    @RequestMapping("/notice/updateNotice")
    public String updateNotice(Notice notice){
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            //处理前端传递过来的时间
            Date releaseDate = new Date();
            notice.setReleaseDate(releaseDate);

            //调用修改博客信息的方法
            int count = noticeService.updateNotice(notice);
            if(count>0){//修改成功
                //更新索引库信息

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
