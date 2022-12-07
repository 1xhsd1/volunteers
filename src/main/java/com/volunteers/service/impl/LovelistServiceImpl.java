package com.volunteers.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteers.entity.Lovelist;
import com.volunteers.dao.LovelistMapper;
import com.volunteers.service.LovelistService;
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
public class LovelistServiceImpl extends ServiceImpl<LovelistMapper, Lovelist> implements LovelistService {
    @Resource
    private LovelistMapper lovelistMapper;

    @Override
    public List<Lovelist> findLoveList() throws Exception {
        QueryWrapper<Lovelist> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("releasseDate");
        return lovelistMapper.selectList(wrapper);
    }

    @Override
    public Lovelist findFavouriteById(Integer id) throws Exception {
        QueryWrapper<Lovelist> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return lovelistMapper.selectOne(wrapper);
    }

    @Override
    public int deleteLoveList(Integer id) throws Exception {
        return lovelistMapper.deleteById(id);
    }

    /**
     * 添加爱心榜
     * @param loveList
     * @return
     * @throws Exception
     */
    @Override
    public int addLoveList(Lovelist loveList) throws Exception {
        //调用新增的方法
        return lovelistMapper.insert(loveList);
    }


    /**
     * 修改爱心榜
     * @param lovelist
     * @return
     * @throws Exception
     */
    @Override
    public int updateLoveList(Lovelist lovelist) throws Exception {
        //调用修改的方法
        return lovelistMapper.updateById(lovelist);
    }

}
