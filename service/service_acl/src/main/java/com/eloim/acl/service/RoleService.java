package com.eloim.acl.service;

import com.eloim.acl.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eloim
 * @since 2021-06-05
 */
public interface RoleService extends IService<Role> {

    Map<String, Object> findRoleByUserId(String userId);

    void saveUserRoleRelationship(String userId, String[] roleId);

    List<Role> selectRoleByUserId(String id);
}
