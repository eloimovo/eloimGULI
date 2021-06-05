package com.eloim.order.client;

import com.eloim.servicebase.entity.CourseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name ="service-edu")
@Component
public interface CourseClient {

    @PostMapping ("/eduService/courseFront/getCourseVoById/{id}")
     CourseVo getCourseVoById(@PathVariable String id);
}
