package cn.practices.redisqueue;

/**
 * @Description: redis队列接口
 * @Author: jingwentao
 * @Date: 2020/6/30
 */
public interface RedisQueue {

    void push(String data);
}
