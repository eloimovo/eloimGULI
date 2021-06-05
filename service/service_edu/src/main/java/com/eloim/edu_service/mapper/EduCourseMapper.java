package com.eloim.edu_service.mapper;

import com.eloim.edu_service.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eloim.edu_service.entity.vo.CoursePublishVo;
import com.eloim.edu_service.entity.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author eloim
 * @since 2021-05-19
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getPublishCourseInfo(String courseID);

    CourseWebVo getBaseCourseInfo(String id);

}
