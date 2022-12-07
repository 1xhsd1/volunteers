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
@TableName("t_lovelist")
public class Lovelist implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 内容
     */
    private String content;

    /**
     * 标题
     */
    private String title;

    /**
     * 发布日期
     */
    @TableField("releasseDate")
    private Date releasseDate;

    /**
     * 图片
     */
    @TableField("img")
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleasseDate() {
        return releasseDate;
    }

    public void setReleasseDate(Date releasseDate) {
        this.releasseDate = releasseDate;
    }

    @Override
    public String toString() {
        return "Lovelist{" +
        "id=" + id +
        ", summary=" + summary +
        ", content=" + content +
        ", title=" + title +
        ", releasseDate=" + releasseDate +
        "}";
    }
}
