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
     * 根据房间id查找评论
     * @param id
     * @return
     * @throws Exception
     */
    List<Comment> findCommentByEventId(Integer id) throws Exception;

    /**
     * 根据房间id删除评论
     * @param eventId
     * @return
     * where b.video_id = d.id and b.create_time between "2023-02-15 00:00:00" and "2023-02-17 00:00:00"
     * and b.delete_status = 0 and d.user_id = 5;
     */
    int deleteCommentByEventId(Integer eventId) throws Exception;

    /**
     * 根据用户名获取评论列表
     * @return
     * @throws Exception
     */
    List<Comment> findCommentsByUsername(String username) throws Exception;

    List<Comment> findAllComment() throws Exception;

    int changeState(int id, int state) throws Exception;
}
