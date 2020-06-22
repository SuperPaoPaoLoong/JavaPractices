package cn.gglol.java.bestpractices;

import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @Description: Bean工具
 * @Author: jingwentao
 * @Date: 2020/6/22
 */
public class BeanUtils {

    private static final Map<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<>(16);


    /**
     * 对象属性拷贝
     *
     * @param source 数据源类
     * @param target 目标类::new(eg: UserVO::new)
     * @return
     */
    public static <S, T> T copyProperties(S source, Supplier<T> target) {
        T t = target.get();

        String beanKey = generateKey(source.getClass(), t.getClass());
        BeanCopier copier = beanCopierMap.computeIfAbsent(beanKey, key -> BeanCopier.create(source.getClass(), t.getClass(), false));

        copier.copy(source, t, null);

        return t;
    }

    /**
     * 集合数据的拷贝
     *
     * @param sources: 数据源类
     * @param target:  目标类::new(eg: UserVO::new)
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }


    /**
     * 带回调函数的集合数据的拷贝（可自定义字段拷贝规则）
     *
     * @param sources:  数据源类
     * @param target:   目标类::new(eg: UserVO::new)
     * @param callBack: 回调函数
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = copyProperties(source, target);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }

    @FunctionalInterface
    public interface BeanCopyUtilCallBack<S, T> {

        /**
         * 定义默认回调方法
         *
         * @param t
         * @param s
         */
        void callBack(S t, T s);
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.getName() + ":" + class2.getName();
    }
}
