package com.volunteers.controller.admin;

import com.alibaba.fastjson.JSON;
import com.volunteers.entity.Event;
import com.volunteers.lucene.EventIndex;
import com.volunteers.service.CommentService;
import com.volunteers.service.EventService;
import com.volunteers.service.FavouriteService;
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
public class EventAdminController {
    @Resource
    private EventService eventService;

    @Resource
    private FavouriteService favouriteService;

    @Resource
    private EventIndex eventIndex;

    @Resource
    private CommentService commentService;

    /**
     * 进入志愿活动管理
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String eventManagement(Model model){
        try {
            List<Event> eventList = eventService.findAllEvent();
            model.addAttribute(eventList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/eventManagement";
    }

    /**
     * 发布活动
     * @param event
     * @return
     */
    @ResponseBody
    @PostMapping("/event/addEvent")
    public String addEvent(Event event, String releaseDateStr, String runDateStr){
        Map<String,Object> map = new HashMap<>();
        try {

            //处理前端传递过来的时间
            //传过来的时间格式为 “2022-02-09T10:21” 中间有一个T，所以我们要把T去掉
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date releaseDate = dateFormat.parse(releaseDateStr.replace("T"," "));
            Date runDate = dateFormat.parse(runDateStr.replace("T"," "));

            event.setReleaseDate(releaseDate);
            event.setRunDate(runDate);

            //调用新增活动的方法
            int count = eventService.addEvent(event);
            //发布成功后，将活动的相关信息放到lucene索引库中、
            eventIndex.addIndex(event);
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
     * 删除博客
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/event/delete/{id}")
    public String deleteBlog(@PathVariable("id") int id) throws Exception{
        //删除对应评论
        commentService.deleteCommentByEventId(id);
        //删除对应收藏
        favouriteService.deleteByEventId(id);
        //删除lucene索引
        String stringId = String.valueOf(id);
        eventIndex.deleteIndex(stringId);
        //删除活动
        eventService.deleteEventById(id);
        return "redirect:/admin/index";

    }

    /**
     *修改活动
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/event/update/{id}")
    public  String toUpdateBlog(@PathVariable("id")int id, Model model) throws Exception{
        model.addAttribute("eventId", id);
        return "/admin/updateEvent";
    }

    /**
     * 处理前端请求，将数据渲染
     * @param eventId
     * @return
     */
    @ResponseBody
    @RequestMapping("/event/findEventById")
    public Object findEventById(Integer eventId){

        try {
            Event event = eventService.findEventById(eventId);

            return  event;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 接收数据并修改活动
     * @param event
     * @return
     */
    @ResponseBody
    @RequestMapping("/event/updateEvent")
    public String updateEvent(Event event, String releaseDateStr, String runDateStr){
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            //处理前端传递过来的时间
            //传过来的时间格式为 “2022-02-09T10:21” 中间有一个T，所以我们要把T去掉
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date releaseDate = dateFormat.parse(releaseDateStr.replace("T"," "));
            Date runDate = dateFormat.parse(runDateStr.replace("T"," "));

            event.setReleaseDate(releaseDate);
            event.setRunDate(runDate);

            //调用修改博客信息的方法
            int count = eventService.updateEvent(event);
            if(count>0){//修改成功
                //更新索引库信息
                eventIndex.updateIndex(event);
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
