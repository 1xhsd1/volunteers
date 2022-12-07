package com.volunteers.dao;

import com.volunteers.entity.Eventtype;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
public interface EventtypeMapper extends BaseMapper<Eventtype> {
    List<Eventtype> findEventTypeNameAndEventCount() throws Exception;
}
