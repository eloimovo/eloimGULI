package com.eloim.edu_service;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.eloim")
@MapperScan(basePackages = {"com/eloim/edu_service/mapper"})
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceApp_8001 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApp_8001.class,args);
    }
}
