package com.volunteers.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
@TableName("t_user")
public class User implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "userId", type = IdType.AUTO)
    private Integer userId;

    /**
     * 服务时长
     */
    @TableField("serviceTime")
    private LocalDateTime serviceTime;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    @TableField("nickName")
    private String nickName;

    /**
     * 签名
     */
    private String profile;

    /**
     * 是否管理员
     */
    @TableField("isManager")
    private Integer isManager;

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(LocalDateTime serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", serviceTime=" + serviceTime +
        ", username=" + username +
        ", password=" + password +
        ", nickName=" + nickName +
        ", profile=" + profile +
        "}";
    }
}
