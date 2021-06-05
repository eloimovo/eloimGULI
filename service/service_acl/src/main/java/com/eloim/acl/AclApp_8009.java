package com.eloim.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.eloim")
@MapperScan("com.eloim.acl.mapper")
@EnableDiscoveryClient
public class AclApp_8009 {
    public static void main(String[] args) {
        SpringApplication.run(AclApp_8009.class,args);
    }
}
