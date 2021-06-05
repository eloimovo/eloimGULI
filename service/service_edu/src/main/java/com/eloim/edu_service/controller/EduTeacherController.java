package com.eloim.edu_service.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.commonutils.Result;
import com.eloim.edu_service.entity.EduTeacher;
import com.eloim.edu_service.entity.vo.TeacherQuery;
import com.eloim.edu_service.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author eloim
 * @since 2021-05-15
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduService/teacher")

public class EduTeacherController {
    @Autowired
    EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public Result findAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("items",list);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("/deleteTeacherById/{id}")
    public Result deleteTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true)
                                     @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @ApiOperation("分页讲师列表")
    @GetMapping("/pageList/{page}/{limit}")
    public Result pageList(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
                           @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        //分页查询，查完后，会将数据封装在pageParam中
        eduTeacherService.page(pageParam,null);
        //获取查询到的数据
        List<EduTeacher> records = pageParam.getRecords();
        //获取总记录数
        long total = pageParam.getTotal();
        return Result.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("讲师条件分页查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@ApiParam(name = "current", value = "当前页码", required = true)@PathVariable long current,
                                       @ApiParam(name = "limit", value = "当前页码", required = true)@PathVariable long limit,
                                       @ApiParam(name = "teacherQuery", value = "条件") @RequestBody(required = false)TeacherQuery teacherQuery){
        Page<EduTeacher> page=new Page<>(current,limit);


        eduTeacherService.pageQuery(page,teacherQuery);

        return Result.ok().data("total",page.getTotal()).data("items",page.getRecords());

    }

    @ApiOperation("添加讲师")
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody EduTeacher teacher){
        boolean flag = eduTeacherService.save(teacher);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }

    }

    @ApiOperation("ID查找讲师")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        if (teacher!=null){
            return Result.ok().data("teacher",teacher);
        }else {
            return Result.error().message("该ID不存在哦");
        }
    }

    //修改讲师
    @ApiModelProperty(value = "修改讲师")
    @PostMapping("/updateById")
    public Result updateById(@RequestBody EduTeacher teacher){
        boolean flag = eduTeacherService.updateById(teacher);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

