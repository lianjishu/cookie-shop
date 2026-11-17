package io.github.lianjishu.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handle(Exception e, HttpServletRequest req) {
        log.error("未处理异常 uri={}", req.getRequestURI(), e);
        return "error"; // 或按你项目现有方式处理
    }
}
