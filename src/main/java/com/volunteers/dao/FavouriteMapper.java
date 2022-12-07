package com.volunteers.dao;

import com.volunteers.entity.Favourite;
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
public interface FavouriteMapper extends BaseMapper<Favourite> {
    /**
     * 根据名字查询收藏列表
     * @param username
     * @return
     * @throws Exception
     */
    List<Favourite> queryList(String username) throws Exception;
}
