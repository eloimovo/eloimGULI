package com.eloim.staservice.schedule;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {

    //（0/5 * * * * ？）：每隔5秒执行一次
    @Scheduled(cron = "0 0 1 * * ?")//指定cron表达式规则
    public void task01() {
        System.out.println("=========执行了");

    }
}

