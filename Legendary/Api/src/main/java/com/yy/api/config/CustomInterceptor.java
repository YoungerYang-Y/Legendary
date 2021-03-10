package com.yy.api.config;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.OutputBuffer;
import org.apache.catalina.connector.ResponseFacade;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/**
 * @program: Legendary
 * @author: yang
 * @date: 2020/12/23 9:29
 * @version: 1.0
 * @description: 自定义拦截器
 */
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {
    
    /**
     * 请求controller前调用
     * 
     * @author: yang
     * @date: 2020/12/23 9:33
     * @param
     * @return: 
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String currentUrl = this.getRequestUri(request);

        log.info("当前拦截的URL为【" + currentUrl + "】");
        long start = System.currentTimeMillis();

        // 拦截后的操作


        long end = System.currentTimeMillis();
        log.info("拦截总花费时间{}ms", end - start);
        return true;
    }

    /**
     * 调用Controller方法之后、视图渲染之前调用
     *
     * @author: yang
     * @date: 2020/12/23 9:34
     * @param
     * @return:
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 渲染视图完成之后使用
     * 
     * @author: yang
     * @date: 2020/12/23 9:34
     * @param
     * @return: 
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 获取URL
     * */
    private String getRequestUri(HttpServletRequest request) {
        String url = request.getRequestURI();
        String root = request.getContextPath();
        if (StrUtil.isNotEmpty(root)) {
            url = url.substring(root.length());
        }

        if (url.indexOf(StrUtil.DOT) > 0) {
            url = url.substring(0, url.indexOf(StrUtil.DOT));
        }

        return url;
    }
}
