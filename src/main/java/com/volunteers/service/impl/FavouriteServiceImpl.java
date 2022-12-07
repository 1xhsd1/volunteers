package com.volunteers.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteers.entity.Favourite;
import com.volunteers.dao.FavouriteMapper;
import com.volunteers.service.FavouriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class FavouriteServiceImpl extends ServiceImpl<FavouriteMapper, Favourite> implements FavouriteService {

    @Resource
    private FavouriteMapper favouriteMapper;

    @Override
    public List<Favourite> queryByName(String username, int eventId) throws Exception {
        QueryWrapper<Favourite> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("eventId", eventId);
        return favouriteMapper.selectList(wrapper);
    }

    @Override
    public int deleteByEventId(Integer eventId) throws Exception {
        QueryWrapper<Favourite> wrapper = new QueryWrapper<>();
        wrapper.eq("eventId", eventId);
        return favouriteMapper.delete(wrapper);
    }

    @Override
    public List<Favourite> queryList(String username) throws Exception {
        return favouriteMapper.queryList(username);
    }

    @Override
    public int deleteFavoritesById(int id) throws Exception {
        return favouriteMapper.deleteById(id);
    }
}
