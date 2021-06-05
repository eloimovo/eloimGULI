package com.eloim.edu_service.controller;


import com.eloim.commonutils.Result;
import com.eloim.edu_service.client.VodClient;
import com.eloim.edu_service.entity.EduVideo;
import com.eloim.edu_service.service.EduVideoService;
import com.eloim.servicebase.entity.GuLiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author eloim
 * @since 2021-05-19
 */
@RestController
@RequestMapping("/eduService/video")

public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return Result.ok();
    }

    @DeleteMapping("/deleteVideoById/{videoId}")
    public Result deleteVideoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(videoSourceId!=null){
            Result result = vodClient.removeAliyunVideoById(videoSourceId);
            if(result.getCode()==20001){
                throw new GuLiException(20001,"Hystrix:删除视频失败。。。");
            }
        }
        eduVideoService.removeById(videoId);
        return Result.ok();
    }


    @PostMapping("/updateVideo")
    public  Result updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return Result.ok();
    }

    @GetMapping("/getVideoById/{videoId}")
    public Result getVideoById(@PathVariable String videoId){
        EduVideo video = eduVideoService.getById(videoId);
        return Result.ok().data("video",video);
    }

}

