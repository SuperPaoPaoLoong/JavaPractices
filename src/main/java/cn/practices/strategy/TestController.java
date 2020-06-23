package cn.practices.strategy;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description: 策略模式两种注入方式 及策略选择方法
 * @Author: jingwentao
 * @Date: 2020/6/23
 */
@RestController
public class TestController {

    @Resource
    Map<String, BaseStrategy> strategyMap;

    @Resource
    List<BaseStrategy> strategyList;

    /**
     * /strategy/sub_/1/2 -> cn.practices.strategy.StrategySub@55e3d6c3 1: 1+2=-1
     * /strategy/add_/1/2 -> cn.practices.strategy.StrategyAdd@3ae0b770 1: 1+2=3
     * /strategy/add/1/2 -> cn.practices.strategy.StrategyAdd@3ae0b770 2: 1+2=3
     * /strategy/sub/1/2 -> cn.practices.strategy.StrategySub@55e3d6c3 2: 1+2=-1
     * /strategy/add1/1/2 -> Strategy is lost
     *
     * @param option
     * @param a
     * @param b
     * @return
     */
    @RequestMapping("/strategy/{option}/{a}/{b}")
    public String option(@PathVariable String option, @PathVariable int a, @PathVariable int b) {
        // 通过 bean 名称依赖查找
        BaseStrategy strategy = strategyMap.get(option);
        if (strategy != null) {
            return strategy.toString() + " 1: " + a + "+" + b + "=" + strategy.option(a, b);
        }

        // 通过自定义参数筛选
        try{
            for (BaseStrategy st : strategyList) {
                if (ObjectUtils.nullSafeEquals(st.getClass().getAnnotation(Strategy.class).key(),
                        EnumStrategy.valueOf(EnumStrategy.class, option))) {
                    return st.toString() + " 2: " + a + "+" + b + "=" + st.option(a, b);
                }
            }
        }catch (Exception ex){
        }

        return "Strategy is lost";
    }
}
