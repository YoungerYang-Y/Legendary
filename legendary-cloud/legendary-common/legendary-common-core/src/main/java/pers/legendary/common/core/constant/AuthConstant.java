package pers.legendary.common.core.constant;

/**
 * Description: 权限相关常量定义
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2022/4/18 21:37
 */
public enum AuthConstant {
    /**
     * JWT存储权限前缀
     */
    AUTHORITY_PREFIX("ROLE_"),
    /**
     * JWT存储权限属性
     */
    AUTHORITY_CLAIM_NAME("authorities"),
    /**
     * 认证信息Http请求头
     */
    JWT_TOKEN_HEADER("Authorization"),
    /**
     * JWT令牌前缀
     */
    JWT_TOKEN_PREFIX("Bearer "),
    /**
     * 用户信息Http请求头
     */
    USER_TOKEN_HEADER("user");

    private final String code;

    AuthConstant(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
