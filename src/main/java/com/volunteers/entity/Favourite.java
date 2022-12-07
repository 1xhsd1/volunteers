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
@TableName("t_favourite")
public class Favourite implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动id
     */
    @TableField("eventId")
    private Integer eventId;

    /**
     * 收藏日期
     */
    @TableField("addDate")
    private Date addDate;

    /**
     * 所属用户
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

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Favourite{" +
        "id=" + id +
        ", eventId=" + eventId +
        ", addDate=" + addDate +
        ", username=" + username +
        "}";
    }
}
