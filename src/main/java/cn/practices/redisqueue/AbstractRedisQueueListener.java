package cn.practices.redisqueue;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: jingwentao
 * @Date: 2020/6/30
 */
@Slf4j
public abstract class AbstractRedisQueueListener implements RedisQueueListener {

    @Resource
    ThreadPoolTaskExecutor redisQueueTaskExecutor;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Getter
    private final String queueName;

    public AbstractRedisQueueListener(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public void run() {
        while (!redisQueueTaskExecutor.getThreadPoolExecutor().isShutdown()) {
            postProcess();
        }
    }

    protected void postProcess() {
        String data = stringRedisTemplate.opsForList().rightPopAndLeftPush(queueName, getActQueueName(), 500, TimeUnit.MILLISECONDS);
        if (data == null) {
            return;
        }
        try {
            handle(data);
            stringRedisTemplate.opsForList().remove(getActQueueName(), 1, data);
        } catch (Exception e) {
            log.warn("onMessage handle exception:{}", data, e);
            stringRedisTemplate.opsForList().leftPush(queueName, data);
            stringRedisTemplate.opsForList().remove(getActQueueName(), 1, data);
        }
    }

    public String getActQueueName() {
        return String.format("%s:doing", queueName);
    }
}
