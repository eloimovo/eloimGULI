package com.eloim.edu_service.controller;


import com.eloim.commonutils.Result;
import com.eloim.edu_service.entity.EduChapter;
import com.eloim.edu_service.entity.chapter.ChapterVo;
import com.eloim.edu_service.service.EduChapterService;
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
@RequestMapping("/eduService/chapter")

public class EduChapterController {


    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("/getChapterById/{courseId}")
    public Result getChapterById(@PathVariable String courseId){
        EduChapter chapter = eduChapterService.getById(courseId);
        return Result.ok().data("chapter",chapter);
    }
    @GetMapping("/getChapterVideoById/{courseId}")
    public Result getChapterVideoById(@PathVariable String courseId){
        List<ChapterVo> list=eduChapterService.getChapterAndVideo(courseId);
        return Result.ok().data("list",list);
    }


    @DeleteMapping("/deleteChapterById/{chapterId}")
    public  Result deleteChapterById(@PathVariable String chapterId){
        boolean flag = eduChapterService.deleteChapter(chapterId);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return Result.ok();
    }
    @PostMapping("/updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return Result.ok();
    }

}

