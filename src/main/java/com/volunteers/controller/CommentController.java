package com.volunteers.controller;


import com.volunteers.dao.EventMapper;
import com.volunteers.dao.EventtypeMapper;
import com.volunteers.entity.Comment;
import com.volunteers.entity.Event;
import com.volunteers.service.CommentService;
import com.volunteers.util.SysConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;
    
    @Resource
    private EventMapper eventMapper;

    /**
     * 添加评论
     * @param eventId
     * @param comments
     * @param model
     * @return
     * @throws Exception
     */
    @RequiresAuthentication
    @PostMapping("/addComment")
//    @ResponseBody
    public String addComment(@RequestParam("eventId") int eventId, @RequestParam("comments") String comments, Model model)throws Exception{
        Comment comment = new Comment();
        Map<String,Object> map = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        //设置评论信息
        if (subject.isAuthenticated()) {
            comment.setCommentDate(new Date());//评论时间就是当前系统时间
            comment.setEventId(eventId);
            comment.setContent(comments);
            comment.setState(0);//未审核
            String principal = (String) subject.getPrincipal();
            comment.setUsername(principal);
            model.addAttribute("userState", true);
            //调用添加评论的方法
            //该save()方法是MyBatisPlus自带的方法
            boolean flag = commentService.save(comment);
        }else {
            model.addAttribute("userState", false);
            return "admin/login";

        }
        //评论成功后返回原页面
        return "redirect:/event/view/"+eventId;
    }

    /**
     * 根据用户名获取评论列表
     * @return
     */
    @RequestMapping("/toCommentManage")
    public String getCommentList(Model model) throws Exception{
        //获取用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        //获取评论列表
        List<Comment> comments = commentService.findCommentsByUsername(username);
        model.addAttribute("comments",comments);
        return "admin/commentManage";
    }


    /**
     * 获取全部评论
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/toCheck")
    public String commentCheck(Model model) throws Exception{
        List<Comment> comments = commentService.findAllComment();
        model.addAttribute("comments", comments);
        return "admin/commentCheck";
    }

    /**
     * 评论通过
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/yes/{id}/{eventId}")
    public String passComment(@PathVariable int id,@PathVariable int eventId) throws Exception{
        commentService.changeState(id, SysConstant.COMMENT_STATE_OK);
        //评论保存成功后调用评论数+1的方法
        Event event = eventMapper.findEventById(eventId);
        //修改阅读数量
        event.setReplyHit(event.getReplyHit()+1);
        //调用修改方法
        eventMapper.updateById(event);
        return "redirect:/comment/toCheck";
    }

    /**
     * 评论不通过
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/no/{id}")
    public String noPassComment(@PathVariable int id) throws Exception{
        commentService.changeState(id, SysConstant.COMMENT_STATE_NO);
        return "redirect:/comment/toCheck";
    }
}

