package com.volunteers.controller;


import com.volunteers.entity.Lovelist;
import com.volunteers.entity.Notice;
import com.volunteers.service.NoticeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.websocket.server.PathParam;
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
@RequestMapping("/notice")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    /**
     * 查看全部社区公告
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("toNoticeList")
    public String findNoticeList(Model model) throws Exception{
        List<Notice> noticeList = noticeService.findNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "foreign/noticeList";
    }

    /**
     * 查看博客详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/{id}")
    public String noticeDetail(@PathVariable Integer id, Model model) {
        try {
            Notice notice = noticeService.findNoticeById(id);
            model.addAttribute("notice", notice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "foreign/noticeDetail";
    }

    /**
     * 社区公告管理
     * @return
     */
    @RequestMapping("/toNoticeManage")
    public String toLoveListManage(Model model){
        try {
            List<Notice> noticeList = noticeService.findNoticeList();
            model.addAttribute("noticeList", noticeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/noticeManage";
    }

    /**
     * 根据id删除社区公告
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deleteLoveList(@PathVariable Integer id){
        try {
            noticeService.deleteNotice(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/notice/toNoticeManage";
    }

}

