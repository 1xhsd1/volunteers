package com.volunteers.dao;

import com.volunteers.entity.User;
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
public interface UserMapper extends BaseMapper<User> {

    List queryBloggerList(String username) throws Exception;
}
