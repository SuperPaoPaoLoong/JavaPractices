package cn.practices.redisqueue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 线程池bean 配置
 * @Author: jingwentao
 * @Date: 2020/6/30
 */
@Configuration
public class RedisQueueConfigurer {

    @Bean
    public ThreadPoolTaskExecutor redisQueueTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(0);
        executor.setKeepAliveSeconds(10);
        executor.setDaemon(true);
        executor.setThreadNamePrefix("mq-executor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }

    @Bean
    DemoRedisQueue demoRedisQueue() {
        return new DemoRedisQueue("redisQueue::test", 10);
    }

    @Bean
    DemoRedisQueueListener demoRedisQueueListener() {
        return new DemoRedisQueueListener("redisQueue::test");
    }
}
