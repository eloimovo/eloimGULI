package com.eloim.edu_service.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.commonutils.Result;
import com.eloim.edu_service.entity.EduCourse;

import com.eloim.edu_service.entity.vo.CourseInfo;
import com.eloim.edu_service.entity.vo.CoursePublishVo;
import com.eloim.edu_service.entity.vo.CourseQuery;

import com.eloim.edu_service.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author eloim
 * @since 2021-05-19
 */
@RestController
@RequestMapping("/eduService/course")

public class EduCourseController {

    @Autowired
    EduCourseService eduCourseService;

    @PostMapping("/addCourse")
    public Result addCourse(@RequestBody CourseInfo courseInfo) {
        String id = eduCourseService.saveCourseInfo(courseInfo);

        return Result.ok().data("courseId", id);
    }

    @GetMapping("/getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId) {

        CourseInfo courseInfo = eduCourseService.getCourseInfo(courseId);
        return Result.ok().data("course", courseInfo);
    }

    //修改课程信息
    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfo courseInfo) {
        eduCourseService.updateCourseInfo(courseInfo);
        return Result.ok();
    }


    @GetMapping("/getCoursePublishInfo/{courseId}")
    public Result getCoursePublishInfo(@PathVariable String courseId) {
        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublish(courseId);
        return Result.ok().data("result", coursePublishVo);
    }

    //课程最终发布
//修改课程状态
    @GetMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus("Normal"); //设置课程发布状态
        eduCourse.setId(id);
        boolean flag = eduCourseService.updateById(eduCourse);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation(value = "所有课程列表")
    @GetMapping("/findAll")
    public Result findAll() {
        List<EduCourse> list = eduCourseService.list(null);
        return Result.ok().data("items", list);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("/deleteCourseById/{id}")
    public Result deleteCourseById(@PathVariable String id) {
        boolean flag = eduCourseService.removeCourse(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    @ApiOperation("分页课程列表")
    @GetMapping("/pageList/{page}/{limit}")
    public Result pageList(@ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
                           @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit) {
        Page<EduCourse> pageParam = new Page<>(page, limit);
        //分页查询，查完后，会将数据封装在pageParam中
        eduCourseService.page(pageParam, null);
        //获取查询到的数据
        List<EduCourse> records = pageParam.getRecords();
        //获取总记录数
        long total = pageParam.getTotal();
        return Result.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("课程条件分页查询")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public Result pageCourseCondition(@ApiParam(name = "current", value = "当前页码", required = true) @PathVariable long current,
                                      @ApiParam(name = "limit", value = "当前页码", required = true) @PathVariable long limit,
                                      @ApiParam(name = "courseQuery", value = "条件") @RequestBody(required = false) CourseQuery courseQuery) {
        Page<EduCourse> page = new Page<>(current, limit);


        eduCourseService.pageQuery(page, courseQuery);

        return Result.ok().data("total", page.getTotal()).data("items", page.getRecords());

    }
}

