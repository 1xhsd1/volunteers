package com.volunteers.controller.admin;

import com.volunteers.service.EventtypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin/eventtype")
public class EventTypeAdminController {

    @Resource
    private EventtypeService eventtypeService;


    /**
     * 查询博客分类下拉列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/typeItem")
    public String typeItem(){
        try {
            //查询活动类别
            String typeList = eventtypeService.findEventTypeNameAndEventCount();
            return typeList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}