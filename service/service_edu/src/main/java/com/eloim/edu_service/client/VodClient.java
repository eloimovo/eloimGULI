package com.eloim.edu_service.client;


import com.eloim.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "service-vod")
public interface  VodClient {


    //根据视频id删除阿里云视频
    @DeleteMapping("/vodService/removeAliyunVideoById/{id}")
     Result removeAliyunVideoById(@PathVariable String id);

    @DeleteMapping("/vodService//removeBatch")
     Result removeBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
