package com.eloim.acl.service;

import com.eloim.acl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-06-05
 */
public interface UserService extends IService<User> {

    com.eloim.acl.entity.User selectByUsername(String username);

}
