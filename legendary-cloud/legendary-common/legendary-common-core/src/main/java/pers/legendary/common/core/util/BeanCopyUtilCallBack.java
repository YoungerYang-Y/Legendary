package pers.legendary.common.core.util;

/**
 * Description:
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2023-02-03 22:46:56
 */
@FunctionalInterface
public interface BeanCopyUtilCallBack<S, T>{
    /**
     * 定义默认回调方法
     * 
     * @author YangYang
     * @date 2023-02-03 22:47
     * @param t -
     * @param s -
     */
    void callBack(S t, T s);
}
