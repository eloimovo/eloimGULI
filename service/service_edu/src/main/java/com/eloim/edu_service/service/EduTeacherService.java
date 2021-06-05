package com.eloim.edu_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eloim.edu_service.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eloim.edu_service.entity.vo.TeacherQuery;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-05-15
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

    Map<String, Object> getTeacherFrontPageList(Page<EduTeacher> teacherPage);

}
