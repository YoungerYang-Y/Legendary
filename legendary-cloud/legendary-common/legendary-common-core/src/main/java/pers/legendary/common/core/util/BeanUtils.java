package pers.legendary.common.core.util;

import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Description: 扩展hutool的BeanUtil类
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2023-02-03 22:44:46
 */
public class BeanUtils extends BeanUtil {

    /**
     * 深拷贝List
     *
     * @author YangYang
     * @date 2023-02-03 22:49
     * @param sources 被拷贝对象
     * @param target 目标类::new(eg: UserVO::new)
     * @return 深拷贝结果
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * 深拷贝List
     *
     * @author YangYang
     * @date 2023-02-03 22:49
     * @param sources 被拷贝对象
     * @param target 目标类::new(eg: UserVO::new)
     * @param callBack 回调函数
     * @return 深拷贝结果
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopyUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }

}
