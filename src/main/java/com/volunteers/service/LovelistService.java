package com.volunteers.service;

import com.volunteers.entity.Lovelist;
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
public interface LovelistService extends IService<Lovelist> {
    /**
     * 查询所有员工榜单
     * @return
     * @throws Exception
     */
    List<Lovelist> findLoveList(String manager) throws Exception;

    /**
     * 根据id查找员工榜详情
     * @param id
     * @return
     * @throws Exception
     */
    Lovelist findFavouriteById(Integer id) throws Exception;

    /**
     * 根据id删除员工榜
     * @return
     * @throws Exception
     */
    int deleteLoveList(Integer id) throws Exception;


    /**
     * 新增员工榜
     * @param loveList
     * @return
     * @throws Exception
     */
    int addLoveList(Lovelist loveList) throws Exception;

    /**
     * 修改员工榜
     * @param lovelist
     * @return
     * @throws Exception
     */
    int updateLoveList(Lovelist lovelist) throws Exception;
}
