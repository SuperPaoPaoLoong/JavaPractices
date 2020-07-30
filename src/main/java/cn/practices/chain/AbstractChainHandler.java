package cn.practices.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @Author: jingwentao
 * @Date: 2020/7/29
 */
@Slf4j
public abstract class AbstractChainHandler implements BaseHandler {
    public void handle(HandlerChain chain) {
        try {
            if (match(chain)) {
                preHandle(chain);
                doHandle(chain);
                postHandle(chain);
            }
        } catch (Exception e) {
            log.error("BaseChain {} chain {} handle error {}", this.getClass().getName(), chain, e);
        }
    }

    abstract boolean match(HandlerChain context);

    abstract void preHandle(HandlerChain context);

    abstract void doHandle(HandlerChain context);

    abstract void postHandle(HandlerChain context);
}
