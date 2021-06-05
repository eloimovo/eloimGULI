package com.eloim.oos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.eloim")
@EnableDiscoveryClient
public class OssAPP_8002 {
    public static void main(String[] args) {
        SpringApplication.run(OssAPP_8002.class,args);
    }
}
