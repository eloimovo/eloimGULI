package com.eloim.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.eloim.acl.entity.User;
import com.eloim.acl.mapper.UserMapper;
import com.eloim.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author eloim
 * @since 2021-06-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {

        return  baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));

    }
}
