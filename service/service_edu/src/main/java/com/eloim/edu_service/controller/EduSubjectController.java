package com.eloim.edu_service.controller;


import com.eloim.commonutils.Result;
import com.eloim.edu_service.entity.subject.OneSub;
import com.eloim.edu_service.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author eloim
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/eduService/subject")

@Api(description="课程管理系统")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/saveSub")
    public Result saveSub(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return Result.ok().message("上传成功");
    }

    @GetMapping("/getAll")
    public  Result getAll(){
        List<OneSub> subjects = eduSubjectService.getAllSubjects();
        return Result.ok().data("subjects",subjects);
    }

}

