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
     * 分页查询房间
     * @param page
     * @param event
     * @return
     */
    IPage<Event> findEventByPage(IPage<Event> page, Event event);

    /**
     * 按日期分类房间
     * @return
     * @throws Exception
     */
    String findEventDateAndCount() throws Exception;

    /**
     * 根据id查找房间
     * @param id
     * @return
     * @throws Exception
     */
    Event findEventById(int id) throws Exception;

    /**
     * 查询所有房间
     * @return
     * @throws Exception
     */
    List<Event> findAllEvent() throws Exception;

    /**
     * 新增房间
     * @param event
     * @return
     * @throws Exception
     */
    int addEvent(Event event) throws Exception;

    /**
     * 撤回房间
     * @param id
     * @return
     * @throws Exception
     */
    int deleteEventById(Integer id) throws Exception;

    /**
     * 修改房间
     * @param event
     * @return
     * @throws Exception
     */
    int updateEvent(Event event) throws Exception;


}
