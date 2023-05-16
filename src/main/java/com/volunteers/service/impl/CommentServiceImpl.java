package com.volunteers.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteers.entity.Comment;
import com.volunteers.dao.CommentMapper;
import com.volunteers.entity.Event;
import com.volunteers.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volunteers.util.SysConstant;
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
        wrapper.eq("state", SysConstant.COMMENT_STATE_OK);
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

    @Override
    public List<Comment> findAllComment() throws Exception {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        return commentMapper.selectList(wrapper);
    }

    @Override
    public int changeState(int id, int state) throws Exception {
        return commentMapper.changeState(id, state);
    }
}
