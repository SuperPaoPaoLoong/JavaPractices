package cn.practices.chain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: jingwentao
 * @Date: 2020/7/29
 */
@Slf4j
@Order(1)
@Component
public class CHandler extends AbstractChainHandler {
    @Override
    boolean match(HandlerChain context) {
        return true;
    }

    @Override
    void preHandle(HandlerChain context) {
        log.info("{}#preHandle({})", this.getClass().getSimpleName(), context);
    }

    @Override
    void doHandle(HandlerChain context) {
        log.info("{}#doHandle({})", this.getClass().getSimpleName(), context);
    }

    @Override
    void postHandle(HandlerChain context) {
        log.info("{}#postHandle({})", this.getClass().getSimpleName(), context);
    }
}
