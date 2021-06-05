package com.eloim.msm.controller;


import com.aliyuncs.utils.StringUtils;
import com.eloim.commonutils.RandomUtil;
import com.eloim.commonutils.Result;
import com.eloim.msm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //发送短信的方法
    @GetMapping("/sendMsm/{phone}")
    public Result sendMsm(@PathVariable String phone){
        //从redis获取验证码，如果能获取，直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return Result.ok();
        }

        //获取不到就阿里云发送
        //生成随机值，并传递给阿里云短信，让他转发给手机
        code = RandomUtil.getSixBitRandom();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",code);

        //调用service中发送短信的方法
        boolean isSend = msmService.sendMsm(map, phone);
        if (isSend){
            //如果发送成功，把发送成功的code验证码保存到redis中，并设置有效时间，设置5分钟过期
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok();
        }else {
            return Result.error().message("短信发送失败");
        }

    }

    //阿里云短信服务没申请成功！！！
    @GetMapping("/send/{phone}")
    public Result send(@PathVariable String phone){
        return Result.ok();
    }


}
