package com.volunteers.service;

import com.volunteers.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
public interface CommentService extends IService<Comment> {
    /**
     * 根据活动id查找评论
     * @param id
     * @return
     * @throws Exception
     */
    List<Comment> findCommentByEventId(Integer id) throws Exception;

    /**
     * 根据活动id删除评论
     * @param eventId
     * @return
     * @throws Exception
     */
    int deleteCommentByEventId(Integer eventId) throws Exception;

    /**
     * 根据用户名获取评论列表
     * @return
     * @throws Exception
     */
    List<Comment> findCommentsByUsername(String username) throws Exception;
}
