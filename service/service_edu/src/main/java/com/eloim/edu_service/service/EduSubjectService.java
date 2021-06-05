package com.eloim.edu_service.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.eloim.edu_service.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eloim.edu_service.entity.subject.OneSub;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author eloim
 * @since 2021-05-18
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSub> getAllSubjects();
}
