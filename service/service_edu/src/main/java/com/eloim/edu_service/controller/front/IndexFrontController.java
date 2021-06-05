package com.eloim.edu_service.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eloim.commonutils.Result;
import com.eloim.edu_service.entity.EduCourse;
import com.eloim.edu_service.entity.EduTeacher;
import com.eloim.edu_service.service.EduCourseService;
import com.eloim.edu_service.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduService/indexFront")

public class IndexFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询前8条热门课程，查询前4个名师
    @GetMapping("/index")
//    @Cacheable(value = "teacherAndCourse", key = "'list'")
    public Result index(){
        //查询前4条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_count");
        wrapper.last("limit 4");
        List<EduCourse> courseList = eduCourseService.list(wrapper);

        //查询前4张名师
        QueryWrapper<EduTeacher> wrapper1 = new QueryWrapper<>();
        wrapper1.orderByDesc("id");
        wrapper1.last("limit 4");
        List<EduTeacher> teacherList = eduTeacherService.list(wrapper1);


        return Result.ok().data("courseList",courseList).data("teacherList",teacherList);
    }

}
