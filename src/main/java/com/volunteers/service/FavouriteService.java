package com.volunteers.service;

import com.volunteers.entity.Favourite;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaohe
 * @since 2022-10-06
 */
public interface FavouriteService extends IService<Favourite> {
    /**
     * 判断是否收藏过
     * @param username
     * @return
     * @throws Exception
     */
    List<Favourite> queryByName(String username, int eventId) throws Exception;

    /**
     * 删除对应志愿活动的全部收藏
     * @param eventId
     * @return
     * @throws Exception
     */
    int deleteByEventId(Integer eventId) throws Exception;

    /**
     * 根据名字查询收藏列表
     * @param username
     * @return
     * @throws Exception
     */
    List<Favourite> queryList(String username) throws Exception;

    /**
     * 根据id删除收藏
     * @param id
     * @return
     * @throws Exception
     */
    int deleteFavoritesById(int id) throws Exception;

}
