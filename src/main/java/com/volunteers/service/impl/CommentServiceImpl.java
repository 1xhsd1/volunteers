package com.volunteers.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteers.entity.Comment;
import com.volunteers.dao.CommentMapper;
import com.volunteers.entity.Event;
import com.volunteers.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Override
    public List<Comment> findCommentByEventId(Integer id) throws Exception {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("eventId",id);
        return commentMapper.selectList(wrapper);
    }

    @Override
    public int deleteCommentByEventId(Integer eventId) throws Exception {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("eventId", eventId);
        return commentMapper.delete(wrapper);
    }

    @Override
    public List<Comment> findCommentsByUsername(String username) throws Exception {
        return commentMapper.findCommentsByUsername(username);
    }
}
