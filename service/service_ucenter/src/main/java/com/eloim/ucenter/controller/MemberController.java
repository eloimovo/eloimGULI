package com.eloim.ucenter.controller;


import com.eloim.commonutils.JwtUtils;
import com.eloim.commonutils.Result;
import com.eloim.servicebase.entity.UserVo;
import com.eloim.ucenter.entity.Member;
import com.eloim.ucenter.entity.vo.LoginVo;
import com.eloim.ucenter.entity.vo.RegisterVo;
import com.eloim.ucenter.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author eloim
 * @since 2021-05-26
 */
@RestController
@RequestMapping("/ucenter/member")

public class MemberController {

    @Autowired
    private MemberService memberService;

    //登录
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody LoginVo loginVo){
        //返回token，使用jwt生成
        String token = memberService.login(loginVo);
        return Result.ok().data("token",token);
    }

    //注册
    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.ok();
    }

    //根据token获取用户信息
    @GetMapping("/getUserInfo")
    @ResponseBody
    public Result getUserInfo(HttpServletRequest request) {
        //调用jwt工具类里面的根据request对象，获取头信息，返回用户id
        String id = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库，根据用户id，获取用户信息
        Member member = memberService.getById(id);

        return Result.ok().data("userInfo", member);
    }
    //根据token获取用户信息
    @PostMapping("/getUserById/{id}")
    public UserVo getUserById(@PathVariable  String id) {
        //调用jwt工具类里面的根据request对象，获取头信息，返回用户id

        //查询数据库，根据用户id，获取用户信息
        Member member = memberService.getById(id);
        UserVo user = new UserVo();
        BeanUtils.copyProperties(member,user);
        return user;
    }

    //根据日期，获取那天注册人数
    @GetMapping("/countRegister/{day}")
    public Result countRegister(@PathVariable String day){
        Integer count = memberService.getCountRegister(day);
        return Result.ok().data("countRegister",count);
    }
}

