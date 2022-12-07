package com.volunteers.controller;


import com.volunteers.entity.Eventtype;
import com.volunteers.service.EventtypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
@Controller
@RequestMapping("/eventtype")
public class EventtypeController {

    @Resource
    private EventtypeService eventtypeService;

    @ResponseBody
    @GetMapping("/typelist")
    public String typelist(){
        try {
            return eventtypeService.findEventTypeNameAndEventCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

