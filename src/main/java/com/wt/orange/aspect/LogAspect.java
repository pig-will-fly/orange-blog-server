package com.wt.orange.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * <p> 日志处理 </p>
 *
 * @author Wang Tao
 * @date 2021-03-04 16:39:27
 */
@Component
@Aspect
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.wt.orange.exception.GlobalExceptionHandler.*(..))" +
            "||execution(* com.wt.orange.security.handler.MyAccessDeniedHandler.*(..))" +
            "||execution(* com.wt.orange.security.MyAuthenticationEntryPoint.*(..))")
    public void log() {
    }

    @After(value = "log()")
    public void after(JoinPoint point) {
        Object[] args = point.getArgs();
        HttpServletRequest request = null;
        Exception ex = null;
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest)
                request = (HttpServletRequest) arg;
            else if (arg instanceof Exception)
                ex = (Exception) arg;
        }
        if (ex != null)
            if (request != null)
                logger.info("{}请求错误:{}", request.getRequestURI(), ex.getMessage(), ex);
            else {
                logger.info(ex.getMessage(), ex);
            }
    }
}
