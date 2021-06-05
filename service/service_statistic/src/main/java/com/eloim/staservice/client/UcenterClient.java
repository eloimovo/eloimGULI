package com.eloim.staservice.client;

import com.eloim.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
    @FeignClient(value = "service-ucenter",fallback = UcenterClientImpl.class)
    public interface UcenterClient {
        //根据日期，获取那天注册人数
        @GetMapping("/ucenter/member/countRegister/{day}")
        public Result countRegister(@PathVariable("day") String day);
    }

