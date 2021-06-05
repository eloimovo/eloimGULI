package com.eloim.edu_service.controller;



import com.eloim.commonutils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(description="后台管理系统")
@RestController
@RequestMapping("/eduService/user")

public class EduLoginController {

    //login
    @PostMapping("/login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    //info
    @GetMapping("/info")
    public Result info(){
        return Result.ok().data("roles","admin").data("name","admin").data("avatar","http://www.weixintouxiang.cn/weixin/20140607090832328.gif");
    }

}