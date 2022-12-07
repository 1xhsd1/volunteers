package com.volunteers.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.volunteers.entity.Event;
import com.volunteers.dao.EventMapper;
import com.volunteers.service.EventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volunteers.service.UserService;
import com.volunteers.util.SysConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {


    @Resource
    private EventMapper eventMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public IPage<Event> findEventByPage(IPage<Event> page, Event event) {
        return eventMapper.findEventByPage(page, event);
    }

    @Override
    public String findEventDateAndCount() throws Exception {
        //先从缓存中读取数据，判断该缓存是否存在数据，如果缓存为空，则查询数据库
        String eventInfo = redisTemplate.opsForValue().get("event_date_count");
        //判断该缓存是否存在数据，如果缓存为空，则查询数据库
        if(eventInfo==null || eventInfo.equals("") || eventInfo.length()==0){
            //查询数据库
            List<Event> eventList = eventMapper.findEventDateAndCount();
            //转换成json字符串
            eventInfo = JSON.toJSONString(eventList);
            //将数据保存到缓存中
            redisTemplate.opsForValue().set("event_date_count",eventInfo);
        }
        return eventInfo;
    }

    @Override
    public Event findEventById(int id) throws Exception {
        Event event = eventMapper.findEventById(id);
        if (event!=null){
            //修改阅读数量
            event.setCilckHit(event.getCilckHit()+1);
            //调用修改方法
            eventMapper.updateById(event);
        }


        return event;
    }

    @Override
    public List<Event> findAllEvent() throws Exception {
        return eventMapper.findAllEvent();
    }


    @Override
    public int addEvent(Event event) throws Exception {
        event.setCilckHit(0);//阅读量
        event.setReplyHit(0);//评论数量
        event.setHaveScale(0);//已报名人数

        //清空两个缓存
        redisTemplate.delete(SysConstant.EVENT_DATE_COUNT);//清空按日期查询的缓存
        redisTemplate.delete(SysConstant.EVENT_NAME_COUNT);//清空按日志类别查询的缓存
        //调用新增的方法
        return eventMapper.insert(event);
    }

    @Override
    public int deleteEventById(Integer id) throws Exception {
        QueryWrapper<Event> wrapper = new QueryWrapper<>();
        wrapper.eq("eventId",id);
        return eventMapper.delete(wrapper);
    }

    @Override
    public int updateEvent(Event event) throws Exception {
        //清空redis缓存
        redisTemplate.delete(SysConstant.EVENT_DATE_COUNT);
        redisTemplate.delete(SysConstant.EVENT_NAME_COUNT);

        return eventMapper.updateById(event);
    }
}
