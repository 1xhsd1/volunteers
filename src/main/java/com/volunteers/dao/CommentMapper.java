package com.volunteers.dao;

import com.volunteers.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 根据用户名搜索评论
     * @param username
     * @return
     * @throws Exception
     */
    List<Comment> findCommentsByUsername(String username) throws Exception;

}
