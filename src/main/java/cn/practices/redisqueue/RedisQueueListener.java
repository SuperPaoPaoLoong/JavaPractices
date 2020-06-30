package cn.practices.redisqueue;

/**
 * @Description: redis队列监听接口
 * @Author: jingwentao
 * @Date: 2020/6/30
 */
public interface RedisQueueListener extends Runnable {

    void handle(String data);
}
