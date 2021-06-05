package com.eloim.edu_service.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.commonutils.Result;
import com.eloim.edu_service.entity.EduCourse;
import com.eloim.edu_service.entity.EduTeacher;
import com.eloim.edu_service.service.EduCourseService;
import com.eloim.edu_service.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduService/teacherFront")

public class TeacherFrontController {

    @Autowired
    EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping("/getTeacherFrontPageList/{page}/{limit}")
    public Result getTeacherFrontList(@PathVariable Long page, @PathVariable Long limit){
        Page<EduTeacher> teacherPage = new Page<>(page, limit);

        Map<String,Object> map = eduTeacherService.getTeacherFrontPageList(teacherPage);

        return Result.ok().data(map);
    }

    @GetMapping("/getTeacherInfo/{id}")
    public Result getTeacherInfo(@PathVariable String id){
        //查询讲师信息
        EduTeacher teacher = eduTeacherService.getById(id);

        //查询讲师所讲课程信息
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> courseList = eduCourseService.list(wrapper);

        return Result.ok().data("teacher",teacher).data("courseList",courseList);
    }

}
