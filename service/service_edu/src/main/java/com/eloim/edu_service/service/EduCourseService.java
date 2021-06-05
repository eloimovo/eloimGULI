package com.eloim.edu_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.edu_service.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eloim.edu_service.entity.vo.*;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-05-19
 */
public interface EduCourseService extends IService<EduCourse> {


    String saveCourseInfo(CourseInfo courseInfo);

    CourseInfo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfo courseInfo);

    CoursePublishVo getCoursePublish(String courseId);

    void pageQuery(Page<EduCourse> page, CourseQuery courseQuery);

    boolean removeCourse(String id);

    Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo);


    CourseWebVo getBaseCourseInfo(String id);

}

