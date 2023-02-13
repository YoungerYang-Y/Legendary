package pers.legendary.common.core.constant;

/**
 * Description: 定义缓存key常量
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2023-02-10 18:54:23
 */
public final class CacheConstant {

    /**
     * 用户信息key前缀
     */
    public static final String USER_DETAIL = "sys:user_detail:";


    private CacheConstant() {
        throw new IllegalStateException("Utility class");
    }
}
