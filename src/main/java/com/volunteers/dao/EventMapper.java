package com.volunteers.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.volunteers.entity.Event;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
public interface EventMapper extends BaseMapper<Event> {
    /**
     * 按日期分类志愿活动
     * @return
     * @throws Exception
     */
    List<Event> findEventDateAndCount() throws  Exception;
    /**
     * 查询最新博客
     * @param page
     * @param event
     * @return
     */
    IPage<Event> findEventByPage(@Param("page")IPage<Event> page, @Param("event")Event event);

    /**
     * 根据id查找活动
     * @param id
     * @return
     * @throws Exception
     */
    Event findEventById(Integer id) throws Exception;

    /**
     * 查询所有志愿活动
     * @return
     * @throws Exception
     */
    List<Event> findAllEvent() throws Exception;
}
