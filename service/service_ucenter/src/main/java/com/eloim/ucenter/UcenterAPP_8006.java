package com.eloim.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.eloim")
@MapperScan(basePackages = "com.eloim.ucenter.mapper")
@EnableDiscoveryClient
public class UcenterAPP_8006 {
    public static void main(String[] args) {
        SpringApplication.run(UcenterAPP_8006.class,args);
    }
}
