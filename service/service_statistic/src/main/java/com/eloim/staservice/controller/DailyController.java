package com.eloim.staservice.controller;


import com.eloim.commonutils.Result;
import com.eloim.staservice.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author eloim
 * @since 2021-06-02
 */
@RestController
@RequestMapping("/staservice/daily")

public class DailyController {

    @Autowired
    private DailyService dailyService;

    //统计某一天注册人数
    @PostMapping("/createStatisticsByDay/{day}")
    public Result createStatisticsByDay(@PathVariable String day){
        dailyService.createStatisticsByDay(day);
        return Result.ok();
    }
    @GetMapping("/showData/{type}/{begin}/{end}")
    public Result showData(@PathVariable String type,@PathVariable String begin,@PathVariable String end){

        Map<String,Object> map = dailyService.getShowData(type,begin,end);

        return Result.ok().data(map);
    }
}

