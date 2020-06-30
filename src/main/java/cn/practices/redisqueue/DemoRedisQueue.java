package cn.practices.redisqueue;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description:
 * @Author: jingwentao
 * @Date: 2020/6/30
 */
public class DemoRedisQueue extends AbstractRedisQueue implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    public DemoRedisQueue(String queueName, Integer threadCount) {
        super(queueName, threadCount);
    }

    @Override
    AbstractRedisQueueListener initListener() {
        return applicationContext.getBean(DemoRedisQueueListener.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
