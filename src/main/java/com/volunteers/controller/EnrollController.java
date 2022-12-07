package com.volunteers.controller;


import com.sun.org.apache.bcel.internal.generic.FSUB;
import com.volunteers.entity.Enroll;
import com.volunteers.entity.Event;
import com.volunteers.service.EnrollService;
import com.volunteers.service.EventService;
import com.volunteers.util.SysConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
@Controller
@RequestMapping("/enroll")
@RabbitListener(queuesToDeclare = @Queue(value = "volunteers", durable = "false"))
public class EnrollController {
    @Resource
    private EnrollService enrollService;

    @Resource
    private EventService eventService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 根据活动id查询标题
     * @param eventId
     * @param model
     * @return
     */
    @RequestMapping("/toEnrollForm/{eventId}")
    public String toEnrollForm(@PathVariable Integer eventId, Model model){

        try {
            Event event = eventService.findEventById(eventId);
            model.addAttribute("event",event);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/enrollForm";
    }

    /**
     * 监听消息队列
     * 消费者进程
     * @param map
     */
    @RabbitHandler
    @RequiresAuthentication
    public void enroll(HashMap<String,String> map){

        //创建对象写入数据库
        Enroll enroll = new Enroll();
        enroll.setEmail(map.get("mail"));
        enroll.setRealName(map.get("realName"));
        enroll.setEventId(Integer.parseInt(map.get("eventId")));
        enroll.setEnrollDate(new Date());
        enroll.setPhone(map.get("phone"));
        enroll.setIdentifyNO(map.get("identifyNO"));
        enroll.setUsername(map.get("username"));
        enroll.setState(SysConstant.UNCHECK_STATE_NO);
        enrollService.save(enroll);

        //活动报名人数+1
        try {
            Event event = eventService.findEventById(Integer.parseInt(map.get("eventId")));
            if (event!=null){
                event.setHaveScale(event.getHaveScale()+1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

