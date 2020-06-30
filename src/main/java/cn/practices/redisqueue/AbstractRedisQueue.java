package cn.practices.redisqueue;

import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: jingwentao
 * @Date: 2020/6/30
 */
public abstract class AbstractRedisQueue<T extends AbstractRedisQueueListener> implements ApplicationRunner, RedisQueue {

    @Resource
    ThreadPoolTaskExecutor redisQueueTaskExecutor;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Getter
    private String queueName;

    @Getter
    private Integer threadCount;

    public AbstractRedisQueue(String queueName, Integer threadCount) {
        this.queueName = queueName;
        this.threadCount = threadCount;
//        if (threadCount > redisQueueTaskExecutor.getMaxPoolSize()) {
//            throw new RuntimeException("the max threadCount is " + redisQueueTaskExecutor.getMaxPoolSize());
//        }
    }

    @Override
    public void push(String data) {
        stringRedisTemplate.opsForList().leftPush(queueName, data);
    }

    @Override
    public void run(ApplicationArguments args) {
        for (int i = 0; i < threadCount; i++) {
            redisQueueTaskExecutor.execute(initListener());
        }
    }

    abstract T initListener();
}
