package com.volunteers.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
public class sendMailController {
    @Resource
    private JavaMailSenderImpl javaMailSender;

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 生产者进程
     * 发送邮件并且将表单放入消息队列
     * @param mail
     * @param realName
     * @param eventId
     * @param phone
     * @param identifyNO
     */
    @PostMapping("sendMail")
    public String sendMail(@RequestParam("mail")String mail, @RequestParam("realName")String realName,
                           @RequestParam("eventId")Integer eventId, @RequestParam("phone")String phone,
                           @RequestParam("identifyNO")String identifyNO, @RequestParam("startTime") String startTime,
                            @RequestParam("endTime") String endTime ){



        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //设置标题
        simpleMailMessage.setSubject("时尚酒店服务平台回复");
        //设置正文
        startTime.replace("T", " ");
        endTime.replace("T", " ");
        simpleMailMessage.setText(realName+"先生/女士，恭喜你预约成功，您的入住时间是"+startTime+"至"+endTime+"请准时到【时尚酒店】办理入职，预祝你入住愉快。如想了解更多信息请前往平台个人中心。");
        //设置发送方和接收方
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setFrom("1687184886@qq.com");

        //发送邮件
        javaMailSender.send(simpleMailMessage);


        Subject subject = SecurityUtils.getSubject();

        //生产者进程，将表单信息放入消息队列
        HashMap<String, String> map = new HashMap<>();
        map.put("realName", realName);
        map.put("mail", mail);
        map.put("phone",phone);
        map.put("eventId",String.valueOf(eventId));
        map.put("identifyNO",identifyNO);
        map.put("username",subject.getPrincipal().toString());
        rabbitTemplate.convertAndSend("volunteers",map);

        return "redirect:/enroll/toEnrollForm/"+eventId;
    }

}
