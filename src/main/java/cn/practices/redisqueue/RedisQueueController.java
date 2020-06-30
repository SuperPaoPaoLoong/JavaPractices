package cn.practices.redisqueue;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: jingwentao
 * @Date: 2020/6/30
 */
@RestController
public class RedisQueueController {

    @Resource
    private DemoRedisQueue demoRedisQueue;

    @RequestMapping("/redisQueue/push/{data}")
    public String option(@PathVariable String data) {
        if (!StringUtils.isEmpty(data))
            demoRedisQueue.push(data);
        return "OK";
    }
}
