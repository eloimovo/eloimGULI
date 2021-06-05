package com.eloim.acl.controller;


import com.eloim.acl.entity.Permission;
import com.eloim.acl.service.PermissionService;
import com.eloim.commonutils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author eloim
 * @since 2021-06-05
 */
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public Result indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenu();
        return Result.ok().data("children",list);
    }
    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        permissionService.removeChildById(id);
        return Result.ok();
    }
    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRelationship(roleId,permissionId);
        return Result.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectMenuByRoleId(roleId);
        return Result.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.ok();
    }

}


