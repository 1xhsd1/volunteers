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
@TableName("t_event")
public class Event implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 活动id
     */
    @TableId(value = "eventId", type = IdType.AUTO)
    private Integer eventId;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 发布日期
     */
    @TableField("releaseDate")
    private Date releaseDate;

    /**
     * 点击量
     */
    @TableField("cilckHit")
    private Integer cilckHit;

    /**
     * 评论量
     */
    @TableField("replyHit")
    private Integer replyHit;

    /**
     * 活动地点
     */
    private String site;

    /**
     * 活动类型
     */
    private Integer type;

    /**
     * 截止报名日期
     */
    @TableField("runDate")
    private Date runDate;

    /**
     * 需要人数
     */
    @TableField("needScale")
    private Integer needScale;

    /**
     * 已报名人数
     */
    @TableField("haveScale")
    private Integer haveScale;

    /**
     * 活动内容
     */
    private String content;

    /**
     * 活动摘要
     */
    private String summary;

    @TableField(exist = false)
    private String releaseDateStr;

    @TableField(exist = false)
    private Eventtype eventtype;

    @TableField(exist = false)
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    //活动内容，无html标签
    @TableField(exist = false)
    private String contentNoTag;

    public String getContentNoTag() {
        return contentNoTag;
    }

    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Eventtype getEventtype() {
        return eventtype;
    }

    public void setEventtype(Eventtype eventtype) {
        this.eventtype = eventtype;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public Integer getEventCount() {
        return eventCount;
    }

    public void setEventCount(Integer eventCount) {
        this.eventCount = eventCount;
    }

    @TableField(exist = false)
    private Integer eventCount;


    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getCilckHit() {
        return cilckHit;
    }

    public void setCilckHit(Integer cilckHit) {
        this.cilckHit = cilckHit;
    }

    public Integer getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Integer replyHit) {
        this.replyHit = replyHit;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getRunDate() {
        return runDate;
    }

    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }

    public Integer getNeedScale() {
        return needScale;
    }

    public void setNeedScale(Integer needScale) {
        this.needScale = needScale;
    }

    public Integer getHaveScale() {
        return haveScale;
    }

    public void setHaveScale(Integer haveScale) {
        this.haveScale = haveScale;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Event{" +
        "eventId=" + eventId +
        ", title=" + title +
        ", releaseDate=" + releaseDate +
        ", cilckHit=" + cilckHit +
        ", replyHit=" + replyHit +
        ", site=" + site +
        ", type=" + type +
        ", runDate=" + runDate +
        ", needScale=" + needScale +
        ", haveScale=" + haveScale +
        ", content=" + content +
        ", summary=" + summary +
        "}";
    }
}
