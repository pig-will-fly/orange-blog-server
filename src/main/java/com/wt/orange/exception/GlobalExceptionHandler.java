package com.wt.orange.exception;

import com.wt.orange.constant.Constant;
import com.wt.orange.vo.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p> 统一异常处理 </p>
 *
 * @author Wang Tao
 * @date 2021-01-18 17:43:08
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>自定义业务异常处理</p>
     * @param e 异常堆栈信息
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-02-18 16:54:03
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseResult exceptionHandler(BusinessException e) {
        logger.error("业务异常处理:",e.getCause());
        return ResponseResult.error(e.getResultEnum());
    }

    /**
     * <p>已认证用户访问需要权限的资源异常处理</p>
     * @param e 异常堆栈信息
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-02-18 16:54:45
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult exceptionHandler(AccessDeniedException e) {
        return ResponseResult.error(Constant.ResultEnum.ACCESS_DENIED).setMessage(e.getLocalizedMessage());
    }

    /**
     * <p>其他所有异常信息处理</p>
     * @param e 异常堆栈信息
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-02-18 16:56:07
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        logger.error("系统错误:",e.getCause());
        return ResponseResult.error(Constant.ResultEnum.ERROR);
    }
}
