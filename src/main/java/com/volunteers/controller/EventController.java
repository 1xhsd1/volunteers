package com.volunteers.controller;



import com.volunteers.entity.Event;
import com.volunteers.lucene.EventIndex;
import com.volunteers.service.CommentService;
import com.volunteers.service.EventService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.security.interfaces.ECPrivateKey;
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
@RequestMapping("/event")
public class EventController {
    @Resource
    private EventIndex eventIndex;

    @Resource
    private EventService eventService;

    @Resource
    private CommentService commentService;

    @ResponseBody
    @GetMapping("/eventDateList")
    public String eventDateList(){
        try {
            return eventService.findEventDateAndCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 查询活动详细信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/view/{id}")
    public String view(@PathVariable int id, Model model){
        try {
            //查看详情
            Event event = eventService.findEventById(id);
            //将博客信息保存到模型中
            model.addAttribute("event",event);
            //                查询当前博客的评论列表
            model.addAttribute("commentList", commentService.findCommentByEventId(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("pageContent","foreign/view");
        return "foreign/view";
    }

    /**
     * 查询志愿活动
     * @param keyWord
     * @param page
     * @param model
     * @return
     */
    @PostMapping("/query")
    public String query(String keyWord, @RequestParam(required = false,defaultValue = "1") Integer page, Model model){
        try {
            //每页显示的数量
            int pageSize =3;
            //调用查询活动的方法
            List<Event> eventList = eventIndex.searchIndex(keyWord);
            //计算集合中的分页
            int toIndex = Math.min(eventList.size(), page * pageSize);
            //将数据放到模型中subList(参数1：开始的下标位置 参数2：结束位置)
            model.addAttribute("eventList",eventList.subList((page-1)*pageSize,toIndex));
            model.addAttribute("total",eventList.size());//查询的结果数量
            model.addAttribute("keyWord",keyWord);
            model.addAttribute("pageContent","foreign/event/result");

            //上一页下一页
//            model.addAttribute("pageCode",getUpAndDownPageCode(page,pageSize,eventList.size(),keyWord));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/result";
    }


}

