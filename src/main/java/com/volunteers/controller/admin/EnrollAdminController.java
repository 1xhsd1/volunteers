package com.volunteers.controller.admin;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;
import com.volunteers.dao.UserMapper;
import com.volunteers.entity.Enroll;
import com.volunteers.entity.Event;
import com.volunteers.entity.Favourite;
import com.volunteers.entity.User;
import com.volunteers.service.EnrollService;
import com.volunteers.service.EventService;
import com.volunteers.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class EnrollAdminController {
    @Resource
    private EnrollService enrollService;
    @Resource
    private UserService userService;

    @Resource
    private EventService eventService;

    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户名查看房间申请
     * @return
     */
    @RequestMapping("/toEnrollView")
    private String enrollView(Model model) throws Exception{
        //获取用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        //获取评论列表
        List<Enroll> enrollList = enrollService.findEnrollByName(username);
        model.addAttribute("enrollList",enrollList);
        return "admin/enrollView";
    }

    /**
     * 预约列表
     * @return
     */
    @RequestMapping("/toEnrollCheck")
    private String enrollCheck(Model model) throws Exception{

        //获取评论列表
        List<Enroll> enrollList = enrollService.queryList();
        model.addAttribute("enrollList",enrollList);
        return "admin/enrollCheck";
    }

    /**
     * 住房
     * @param id
     * @return
     */
    @RequestMapping("/enroll/success/{id}")
    private String enrollSuccess(@PathVariable("id") int id){
        try {
            Enroll enroll = enrollService.findEnrollById(id);
            enroll.setState(1);
            enroll.setInDate(new Date());
            enrollService.updateEnrollById(enroll);

//            //修改个人志愿时长
//            Subject subject = SecurityUtils.getSubject();
//            String username = (String) subject.getPrincipal();
//            User user = userService.findUserByUsername(username);
//
//            Event event = eventService.findEventById(enroll.getEventId());
//
//            //调用修改方法
//            user.setServiceTime(event.getEventTime()+user.getServiceTime());
//            userMapper.updateById(user);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/toEnrollCheck";

    }

    /**
     * 退房
     * @param id
     * @return
     */
    @RequestMapping("/enroll/refuse/{id}")
    private String enrollRefuse(@PathVariable("id") int id){
        try {
            Enroll enroll = enrollService.findEnrollById(id);
            enroll.setState(2);
            enroll.setOutDate(new Date());
            enrollService.updateEnrollById(enroll);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/toEnrollCheck";

    }

    @RequestMapping("/enroll/addEnroll")
    @ResponseBody
    private String addEnroll(Enroll enroll) throws Exception{
        Map<String,Object> map = new HashMap<>();
        try {
            enroll.setInDate(new Date());
            enroll.setState(1);

            int count = enrollService.addEnroll(enroll);
            if(count>0){
                map.put("success",true);//办理入住成功
            }else {
                map.put("success",false);//办理入住失败
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSON.toJSONString(map);
    }

}
