package com.volunteers.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.volunteers.entity.Event;
import com.volunteers.service.EventService;
import com.volunteers.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class navController {
    @Resource
    private EventService eventService;

    @RequestMapping("/about")
    public String goToAbout(){
        return "foreign/about";
    }



    /**
     * 导航到登录页面
     *
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "admin/login";
    }

    /**
     * 导航到注册页面
     * @return
     * @throws Exception
     */
    @RequestMapping("/toRegisty")
    public String toRegisty() throws Exception{
        return "admin/registy";
    }

    /**
     * 导航到发布活动页面
     * @return
     */
    @RequestMapping("/admin/toWriteEvent")
    public String toWriteEvent(){
        return "admin/writeEvent";
    }


    /**
     * 导航到发布爱心榜页面
     * @return
     */
    @RequestMapping("/admin/toLoveList")
    public String toLoveList(){
        return "admin/WriteLoveList";
    }

    /**
     * 导航到发布社区公告页面
     * @return
     */
    @RequestMapping("/admin/toNotice")
    public String toNotice(){
        return "admin/WriteNotice";
    }



    /**
     * 首页查询最新博客列表
     * @param event      博客查询条件
     * @param current   当前页码
     * @param model
     * @return
     */
    @RequestMapping("/toEvent")
    public String index(Event event, @RequestParam(value = "page",defaultValue = "1")Long current, Model model){
        int size = 5;
        //创建分页对象，指定当前页码及每页显示数量
        Page<Event> page = new Page<Event>(current,size);

        StringBuffer param = new StringBuffer();
        //判断查询条件
        if (event!=null){
            if (event.getType()!=null){
                param.append("type="+event.getType());
            }
            if (event.getReleaseDate() != null && !event.getReleaseDate().equals("")) {

                param.append("releaseDate="+event.getReleaseDate());
            }
        }


        //分页查询
        IPage<Event> eventIPage = eventService.findEventByPage(page,event);
        //获取活动数据列表
        List<Event> eventList = eventIPage.getRecords();
        //将对象放到模型中
        model.addAttribute("eventList",eventList);
        model.addAttribute("pageCode", PageUtil.genPagination("index",eventIPage.getTotal(),current.intValue(),size, null));
//        model.addAttribute("test", "hello");
        return "foreign/event";
    }
}
