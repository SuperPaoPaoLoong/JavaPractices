package cn.practices.strategy;

/**
 * @Description: 两值相加
 * @Author: jingwentao
 * @Date: 2020/6/23
 */
@Strategy(key = EnumStrategy.add, value = "add_")
public class StrategyAdd implements BaseStrategy {
    @Override
    public int option(int a, int b) {
        return a + b;
    }
}
