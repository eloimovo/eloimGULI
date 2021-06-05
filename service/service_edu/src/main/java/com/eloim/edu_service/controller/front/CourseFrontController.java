package com.eloim.edu_service.controller.front;

import com.alibaba.nacos.client.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.commonutils.JwtUtils;
import com.eloim.commonutils.Result;
import com.eloim.edu_service.client.OrderClient;
import com.eloim.edu_service.entity.EduCourse;
import com.eloim.edu_service.entity.chapter.ChapterVo;
import com.eloim.edu_service.entity.vo.CourseFrontVo;
import com.eloim.edu_service.entity.vo.CourseWebVo;
import com.eloim.edu_service.service.EduChapterService;
import com.eloim.edu_service.service.EduCourseService;
import com.eloim.servicebase.entity.CourseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduService/courseFront")

public class CourseFrontController {
    @Autowired
    EduCourseService eduCourseService;

    @Autowired
    EduChapterService eduChapterService;

    @Autowired
    OrderClient orderClient;

    @PostMapping("/getCourseFrontList/{page}/{limit}")
    public Result getCourseFrontList(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> coursePage = new Page<>(page, limit);
        Map<String, Object> map = eduCourseService.getCourseFrontList(coursePage, courseFrontVo);
        return Result.ok().data(map);
    }

    @GetMapping("/getCourseFrontInfo/{id}")
    public Result getCourseFrontInfo(@PathVariable String id, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(id);
        boolean isBuyCourse = false;
        //根据课程id，查询章节和小节信息
        List<ChapterVo> chapterVideoList = eduChapterService.getChapterAndVideo(id);
        String memberId= JwtUtils.getMemberIdByJwtToken(request);
        if (!StringUtils.isEmpty(memberId)){
            //根据课程id、用户id，查询课程是否已经购买
            isBuyCourse = orderClient.isBuyCourse(memberId, id);
        }

        return Result.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoList).data("isBuy",isBuyCourse);

    }
    @PostMapping("/getCourseVoById/{id}")
    public CourseVo getCourseVoById(@PathVariable String id){
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(id);
        CourseVo courseVo = new CourseVo();
        BeanUtils.copyProperties(courseWebVo,courseVo);
        return courseVo;
    }
}
