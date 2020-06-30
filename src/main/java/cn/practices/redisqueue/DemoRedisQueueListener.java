package cn.practices.redisqueue;

import lombok.extern.log4j.Log4j2;

/**
 * @Description:
 * @Author: jingwentao
 * @Date: 2020/6/30
 */
@Log4j2
public class DemoRedisQueueListener extends AbstractRedisQueueListener {

    public DemoRedisQueueListener(String queueName) {
        super(queueName);
    }

    @Override
    public void handle(String data) {
        log.info("cn.practices.redisqueue.DemoRedisQueueListener.handle : {} : {}", Thread.currentThread().getName(), data);
    }
}
