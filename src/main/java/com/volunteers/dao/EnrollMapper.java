package com.volunteers.dao;

import com.volunteers.entity.Enroll;
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
public interface EnrollMapper extends BaseMapper<Enroll> {
    /**
     * 根据名字查询报名申请
     * @param username
     * @return
     * @throws Exception
     */
    List<Enroll> findEnrollByName(String username) throws Exception;

    /**
     * 待审批列表
     * @return
     * @throws Exception
     */
    List<Enroll> queryList() throws Exception;
}
