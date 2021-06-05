package com.eloim.msm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.eloim")
public class MsmAPP_8005 {
    public static void main(String[] args) {
        SpringApplication.run(MsmAPP_8005.class,args);
    }
}
