package com.eloim.acl.service;

import com.alibaba.fastjson.JSONObject;
import com.eloim.acl.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-06-05
 */
public interface PermissionService extends IService<Permission> {



    List<Permission> queryAllMenu();

    void removeChildById(String id);

    void saveRolePermissionRelationship(String roleId, String[] permissionId);


    List<Permission> selectMenuByRoleId(String roleId);

    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);

}
