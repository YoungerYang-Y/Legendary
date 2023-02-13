package pers.legendary.common.core.exception;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description: 全局异常处理器
 *
 * @author YangYang
 * @version 1.0.0
 * @date 2023-02-07 22:35:56
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 服务层异常
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<JSONObject> serviceException(ServiceException exception) {
        return new ResponseEntity<>(exception.getResult(), exception.getStatus());
    }
}
