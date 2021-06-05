package com.eloim.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.eloim"})
@EnableDiscoveryClient
public class VodAPP_8003 {
    public static void main(String[] args) {
        SpringApplication.run(VodAPP_8003.class,args);
    }
}
