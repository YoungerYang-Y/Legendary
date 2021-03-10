package com.yy.api.controller.exception;

import com.yy.core.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CustomException: 自定义异常处理
 *
 * @Author: YangYang
 * @Date: 2021/3/3 22:48
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * 错误统一处理
     */
    private Map handleException(ResultCode resultCode){
        Map map = new HashMap(3);
        map.put("code", resultCode.getCode());
        map.put("msg", resultCode.getMessage());
        map.put("data", null);
        return map;
    }

    /**
     * 登录认证异常 401
     */
    @ExceptionHandler({
            UnauthenticatedException.class,
            AuthenticationException.class,
            LockedAccountException.class,
            UnknownAccountException.class})
    public Map authenticationException(){
        return handleException(ResultCode.UNAUTHORIZED);
    }

    /**
     * 权限异常 403
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public Map authorizationException(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception{
        return handleException(ResultCode.FORBIDDEN);
    }

    /**
     * 全局捕捉自定义异常（凡是抛出RuntimeException的异常都会走这里）
     */
    @ExceptionHandler(value = RuntimeException.class)
    public Map runtimeExceptionHandler(RuntimeException ex) {
        Map map = new HashMap(3);
        map.put("code", ResultCode.FAILED.getCode());
        map.put("msg", ex.getMessage());
        map.put("data", null);
        return map;
    }

    /**
     * 方法不支持 405
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Map methodNotSupportHandle(HttpRequestMethodNotSupportedException e) {

        log.error(e.getMessage(),e);
        return handleException(ResultCode.NOT_SUPPORTED_METHOD);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map hibernateValidatorExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuffer sb = new StringBuffer();
        allErrors.forEach(objectError -> {
            FieldError fieldError = (FieldError)objectError;
            sb.append(fieldError.getObjectName()).append(".").append(fieldError.getField())
                    .append(":").append(fieldError.getDefaultMessage());
        });
        Map map = new HashMap(3);
        map.put("code", ResultCode.VALIDATE_FAILED.getCode());
        map.put("msg", sb);
        map.put("data", null);
        return map;
    }
}
