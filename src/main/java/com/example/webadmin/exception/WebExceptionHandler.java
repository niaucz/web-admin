package com.example.webadmin.exception;

import com.example.webadmin.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 */
@Slf4j
@RestControllerAdvice
public class WebExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(WebException.class)
    public R handleKSDException(WebException e) {
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());
        return r;
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error("请联系管理员！");
    }
}
