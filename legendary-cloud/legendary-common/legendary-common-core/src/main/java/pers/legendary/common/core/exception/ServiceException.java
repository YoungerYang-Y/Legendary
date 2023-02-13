package pers.legendary.common.core.exception;


import cn.hutool.json.JSONObject;
import org.springframework.http.HttpStatus;

/**
 * Description: 服务层异常
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2023-02-06 21:45:07
 */
public class ServiceException extends RuntimeException {

    private final HttpStatus status;

    private final String message;

    /**
     * 自定义异常信息，状态码为500
     */
    public ServiceException(String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    /**
     * 指定异常状态
     */
    public ServiceException(HttpStatus status) {
        this(status, status.getReasonPhrase());
    }

    /**
     * 指定异常状态与异常信息
     */
    public ServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public JSONObject getResult() {
        JSONObject result = new JSONObject();
        result.set("code", status.value());
        result.set("message", message);
        return result;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
