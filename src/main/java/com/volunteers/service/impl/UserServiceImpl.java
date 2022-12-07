package com.volunteers.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volunteers.entity.User;
import com.volunteers.dao.UserMapper;
import com.volunteers.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volunteers.util.SysConstant;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) throws Exception {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public List queryBloggerList(String username) throws Exception {
        return userMapper.queryBloggerList(username);
    }

    @Override
    public int registy(String username, String password) throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsManager(SysConstant.NON_MANAGER);
        return userMapper.insert(user);
    }
}
