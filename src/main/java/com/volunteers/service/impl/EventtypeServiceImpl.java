package com.volunteers.service.impl;

import com.alibaba.fastjson.JSON;
import com.volunteers.dao.EventMapper;
import com.volunteers.entity.Eventtype;
import com.volunteers.dao.EventtypeMapper;
import com.volunteers.service.EventtypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class EventtypeServiceImpl extends ServiceImpl<EventtypeMapper, Eventtype> implements EventtypeService {

    @Resource
    private EventtypeMapper eventtypeMapper;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String findEventTypeNameAndEventCount() throws Exception {
        //从redis缓存中读取博客分类信息
        String eventTypeInfo = redisTemplate.opsForValue().get("event_name_count");
        //判断：redis缓存中是否存在分类的数据，如果缓存中没有数据，此时需要从数据库中查询，查询出来的结果要放到redis缓存中
        //为空，则表示缓存没有数据
        if (eventTypeInfo == null || eventTypeInfo.equals("") || eventTypeInfo.length() == 0) {
            //如果缓存中没有数据，此时需要从数据库中查询，查询出来的结果要放到redis缓存中
            List<Eventtype> eventTypeList = eventtypeMapper.findEventTypeNameAndEventCount();
            //将集合转换成json
            eventTypeInfo = JSON.toJSONString(eventTypeList);
            //将集合的数据放到缓存
            redisTemplate.opsForValue().set("event_name_count", eventTypeInfo);
        }
        return eventTypeInfo;
    }
}
