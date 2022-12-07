package com.volunteers.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.volunteers.entity.Event;
import com.baomidou.mybatisplus.extension.service.IService;
import com.volunteers.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
public interface EventService extends IService<Event> {

    

    /**
     * 分页查询志愿活动
     * @param page
     * @param event
     * @return
     */
    IPage<Event> findEventByPage(IPage<Event> page, Event event);

    /**
     * 按日期分类志愿活动
     * @return
     * @throws Exception
     */
    String findEventDateAndCount() throws Exception;

    /**
     * 根据id查找活动
     * @param id
     * @return
     * @throws Exception
     */
    Event findEventById(int id) throws Exception;

    /**
     * 查询所有志愿活动
     * @return
     * @throws Exception
     */
    List<Event> findAllEvent() throws Exception;

    /**
     * 新增活动
     * @param event
     * @return
     * @throws Exception
     */
    int addEvent(Event event) throws Exception;

    /**
     * 撤回活动
     * @param id
     * @return
     * @throws Exception
     */
    int deleteEventById(Integer id) throws Exception;

    /**
     * 修改活动
     * @param event
     * @return
     * @throws Exception
     */
    int updateEvent(Event event) throws Exception;


}
