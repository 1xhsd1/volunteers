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
public class IndexController {
    @Resource
    private EventService eventService;

    /**
     * 首页查询最新博客列表
     * @param event      博客查询条件
     * @param current   当前页码
     * @param model
     * @return
     */
    @RequestMapping(value = {"/","/index","/index.html"})
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
        return "foreign/index";
    }
}
