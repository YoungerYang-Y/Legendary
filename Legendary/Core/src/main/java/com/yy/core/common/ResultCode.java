package com.yy.core.common;

/**
 * ResultCode: 枚举一些常用API操作码
 *
 * @Author: yangyang
 * @Date: 2021/1/17 14:18
 */
public enum ResultCode implements IErrorCode{

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(400, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    NOT_FOUND(404, "资源不存在"),
    NOT_SUPPORTED_METHOD(405,"不支持的方法");

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
