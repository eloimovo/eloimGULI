package com.eloim.edu_service.client;


import com.eloim.servicebase.entity.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name ="service-ucenter" ,fallback = UcenterClientImpl.class)
@Component
public interface UcenterClient {
    @PostMapping("/ucenter/member/getUserById/{id}")
    UserVo getUserById(@PathVariable String id);
}
