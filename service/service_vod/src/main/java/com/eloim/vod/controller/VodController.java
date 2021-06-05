package com.eloim.vod.controller;

import com.eloim.commonutils.Result;
import com.eloim.servicebase.entity.GuLiException;
import com.eloim.vod.servece.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vodService")

public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("/uploadVideo")
    public Result uploadVideo(MultipartFile file){
        //返回上传视频的id
        String videoId = vodService.uploadVideoAliyun(file);
        return Result.ok().data("videoId",videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("/removeAliyunVideoById/{id}")
    public Result removeAliyunVideoById(@PathVariable String id){
        vodService.removeAliyunVideoById(id);
        return Result.ok();
    }

    //根据id删除多个阿里云视频
    @DeleteMapping("/removeBatch")
    public Result removeBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeMoreVideo(videoIdList);
        return Result.ok();
    }

    //根据视频id获取视频凭证
    @GetMapping("/getPlayAuth/{id}")
    public Result getPlayAuth(@PathVariable String id){
        try {
            String playAddress= vodService.getPlayAddress(id);
            return Result.ok().data("source",playAddress);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuLiException(20001,"获取视频凭证失败");
        }

    }
}
