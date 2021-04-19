package com.vp.tw.scheduler;

import com.vp.tw.scheduler.tasks.Task1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: YangTingCheng
 * @Date: 2020/10/14/上午 08:21
 * @Description:
 */

@Configuration
@EnableScheduling
public class SchedulerConfig {
    @Bean
    public Task1 task() {
        return new Task1();
    }
}
