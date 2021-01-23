package com.wt.orange.exception;

import com.wt.orange.constant.Constant;
import com.wt.orange.vo.ResponseResult;
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

    @ExceptionHandler(BusinessException.class)
    public ResponseResult exceptionHandler(BusinessException e) {
        return ResponseResult.error(e.getResultEnum());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e) {
        return ResponseResult.error(Constant.ResultEnum.ERROR);
    }
}
