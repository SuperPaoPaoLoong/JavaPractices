package cn.practices.chain;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: jingwentao
 * @Date: 2020/7/29
 */
@RestController
public class ChainTestController {

    @Qualifier("old")
    @Resource
    private List<BaseHandler> oldList;

    @RequestMapping("/chain/old")
    public String option() {
        HandlerChain handlerChain = new HandlerChain();
        for (BaseHandler it : oldList) {
            it.handle(handlerChain);
        }
        return "";
    }
}
