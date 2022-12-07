package com.volunteers.service;

import com.volunteers.entity.Eventtype;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
public interface EventtypeService extends IService<Eventtype> {
    /**
     * 查询每个活动分类及分类数目
     * @return
     * @throws Exception
     */
    String findEventTypeNameAndEventCount() throws Exception;
}
