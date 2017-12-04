package hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Date;

@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

    private final int POOL_SIZE = 10;


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {


        scheduledTaskRegistrar.setTaskScheduler(poolScheduler());

        scheduledTaskRegistrar.addFixedDelayTask(new IntervalTask(() -> System.out.println("Job @ fixed rate by Runnable Thread " + new Date() + ", Thread name is " + Thread.currentThread().getName()),1000));
    }

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("my-scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }


}
