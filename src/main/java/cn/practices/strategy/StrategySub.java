package cn.practices.strategy;

/**
 * @Description: 两值相减
 * @Author: jingwentao
 * @Date: 2020/6/23
 */
@Strategy(key = EnumStrategy.sub, value = "sub_")
public class StrategySub implements BaseStrategy {
    @Override
    public int option(int a, int b) {
        return a - b;
    }
}
