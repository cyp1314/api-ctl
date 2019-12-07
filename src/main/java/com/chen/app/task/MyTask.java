package com.chen.app.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@EnableAsync
public class MyTask {
//    @Async
////    @Scheduled(fixedDelay = 5000)  //间隔1秒
//    @Scheduled(cron = "0/5 * * * * ?") //间隔1秒
//    public void task() throws InterruptedException {
//        System.out.println("定时任务巡检...");
//    }
}
