package cn.practices.chain;

/**
 * @Description:责任链模式
 * @Author: jingwentao
 * @Date: 2020/7/29
 */
public interface BaseHandler {
    void handle(HandlerChain context);
}
