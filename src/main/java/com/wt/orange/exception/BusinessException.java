package com.wt.orange.exception;

import com.wt.orange.constant.Constant;

/**
 * <p> 自定义业务异常 </p>
 *
 * @author Wang Tao
 * @date 2021-01-18 17:40:55
 */
public class BusinessException extends RuntimeException {
    private Constant.ResultEnum resultEnum;

    public BusinessException(Constant.ResultEnum resultEnum) {
        super(resultEnum.getValue());
        this.resultEnum = resultEnum;
    }

    public Constant.ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(Constant.ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
    }
}
