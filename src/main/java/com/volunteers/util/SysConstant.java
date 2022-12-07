package com.volunteers.util;

/**
 * 系统常量接口
 */
public interface SysConstant {
    /**
     * 评论审核状态，1表示未审核，2表示已审核通过
     */
    int COMMENT_STATE_OK = 2;
    /**
     * 评论审核状态，1表示未审核，2表示已审核通过
     */
    int COMMENT_STATE_NO = 1;

    /**
     * 当前登录用户的key
     */
    String LOGINUSER = "loginUser";
    /**
     * 活动类别名称和数量的缓存数据
     */
    String EVENT_NAME_COUNT = "event_name_count";

    /**
     * 活动发布日期和数量的缓存数量
     */
    String EVENT_DATE_COUNT = "event_date_count";

    /**
     *报名未审核
     */
    int UNCHECK_STATE_NO = 0;

    /**
     * 报名已审核
     */
    int CHECKED_STATE_NO = 1;

    /**
     * 非管理员身份
     */
    int NON_MANAGER = 0;

}
