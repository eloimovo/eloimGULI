package com.eloim.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(basePackages = "com.eloim.cms.mapper")
@EnableFeignClients
public class CmsAPP_8004 {
    public static void main(String[] args) {
        SpringApplication.run(CmsAPP_8004.class,args);
    }
}
