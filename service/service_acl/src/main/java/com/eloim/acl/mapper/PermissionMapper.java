package com.eloim.acl.mapper;

import com.eloim.acl.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author eloim
 * @since 2021-06-05
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> selectAllPermissionValue();

    List<String> selectPermissionValueByUserId(String id);

    List<Permission> selectPermissionByUserId(String userId);

}
