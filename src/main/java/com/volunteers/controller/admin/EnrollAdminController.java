package com.volunteers.controller.admin;

import com.volunteers.entity.Enroll;
import com.volunteers.entity.Favourite;
import com.volunteers.service.EnrollService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class EnrollAdminController {
    @Resource
    private EnrollService enrollService;

    /**
     * 根据用户名查看活动申请
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
     * 待审批列表
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
     * 通过审核
     * @param id
     * @return
     */
    @RequestMapping("/enroll/success/{id}")
    private String enrollSuccess(@PathVariable("id") int id){
        try {
            Enroll enroll = enrollService.findEnrollById(id);
            enroll.setState(1);
            enrollService.updateEnrollById(enroll);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/toEnrollCheck";

    }

    /**
     * 审核不通过
     * @param id
     * @return
     */
    @RequestMapping("/enroll/refuse/{id}")
    private String enrollRefuse(@PathVariable("id") int id){
        try {
            Enroll enroll = enrollService.findEnrollById(id);
            enroll.setState(2);
            enrollService.updateEnrollById(enroll);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/toEnrollCheck";

    }

}
