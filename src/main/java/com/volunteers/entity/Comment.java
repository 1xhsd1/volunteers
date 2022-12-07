package com.volunteers.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
@TableName("t_comment")
public class Comment implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评论日期
     */
    @TableField("commentDate")
    private Date commentDate;

    /**
     * 活动id
     */
    @TableField("eventId")
    private Integer eventId;

    /**
     * 审核状态
     */
    private Integer state;

    /**
     * 内容
     */
    private String content;

    /**
     * 评论用户
     */
    private String username;

    @TableField(exist = false)
    private Integer temId;

    @TableField(exist = false)
    private String title;

    public Integer getTemId() {
        return temId;
    }

    public void setTemId(Integer temId) {
        this.temId = temId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Comment{" +
        "id=" + id +
        ", commentDate=" + commentDate +
        ", eventId=" + eventId +
        ", state=" + state +
        ", content=" + content +
        ", username=" + username +
        "}";
    }
}
