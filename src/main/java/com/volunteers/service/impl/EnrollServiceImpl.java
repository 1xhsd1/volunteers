package com.volunteers.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteers.entity.Enroll;
import com.volunteers.dao.EnrollMapper;
import com.volunteers.service.EnrollService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Wrapper;
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
public class EnrollServiceImpl extends ServiceImpl<EnrollMapper, Enroll> implements EnrollService {

    @Resource
    private EnrollMapper enrollMapper;


    @Override
    public List<Enroll> findEnrollByName(String username) throws Exception {
        return enrollMapper.findEnrollByName(username);
    }

    @Override
    public List<Enroll> queryList() throws Exception {
        return enrollMapper.queryList();
    }

    @Override
    public Enroll findEnrollById(int id) throws Exception {
        QueryWrapper<Enroll> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return enrollMapper.selectOne(wrapper);
    }

    @Override
    public int updateEnrollById(Enroll enroll) throws Exception {
        return enrollMapper.updateById(enroll);
    }
}
