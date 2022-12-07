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
@TableName("t_enroll")
public class Enroll implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动id
     */
    @TableField("eventId")
    private Integer eventId;

    /**
     * 报名日期
     */
    @TableField("enrollDate")
    private Date enrollDate;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实名字
     */
    @TableField("realName")
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号码
     */
    @TableField("identifyNO")
    private String identifyNO;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;


    /**
     * 审核状态
     */
    @TableField("state")
    private Integer state;

    /**
     * 项目标题
     */
    @TableField(exist = false)
    private String title;

    @TableField(exist = false)
    private Integer temId;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentifyNO() {
        return identifyNO;
    }

    public void setIdentifyNO(String identifyNO) {
        this.identifyNO = identifyNO;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Enroll{" +
        "id=" + id +
        ", eventId=" + eventId +
        ", enrollDate=" + enrollDate +
        ", username=" + username +
        ", realName=" + realName +
        ", email=" + email +
        ", identifyNO=" + identifyNO +
        ", phone=" + phone +
        "}";
    }
}
