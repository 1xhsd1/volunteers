package com.volunteers.service;

import com.volunteers.entity.User;
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
public interface UserService extends IService<User> {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     * @throws Exception
     */
    User findUserByUsername(String username) throws Exception;
    /**
     * 查找所有博主
     * @return
     * @throws Exception
     */
    List queryBloggerList(String username) throws Exception;

    /**
     * 新用户注册
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    int registy(String username, String password) throws Exception;

}
