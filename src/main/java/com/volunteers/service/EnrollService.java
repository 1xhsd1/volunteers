package com.volunteers.service;

import com.volunteers.entity.Enroll;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
public interface EnrollService extends IService<Enroll> {

    /**
     * 根据用户名查找申请列表
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

    /**
     * 根据id查找报名项
     * @param id
     * @return
     * @throws Exception
     */
    Enroll findEnrollById(int id) throws Exception;


    /**
     * 修改状态
     * @param enroll
     * @return
     * @throws Exception
     */
    int updateEnrollById(Enroll enroll) throws Exception;
}
