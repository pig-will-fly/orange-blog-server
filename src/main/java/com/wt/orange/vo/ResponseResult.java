package com.wt.orange.vo;

import com.google.gson.GsonBuilder;
import com.wt.orange.constant.Constant;

/**
 * <p> 返回响应结果数据 </p>
 *
 * @author Wang Tao
 * @date 2021-01-12 14:51:38
 */
public class ResponseResult<T> {

    /**
     * 结果代码
     */
    private Integer code;
    /**
     * 结果信息
     */
    private String message;
    /**
     * 结果数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public ResponseResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * <p>成功调用并返回数据</p>
     *
     * @param data 返回数据
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-01-12 14:57:52
     */
    public static <T> ResponseResult<T> success(T data) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(Constant.ResultEnum.SUCCESS.getCode());
        responseResult.setMessage(Constant.ResultEnum.SUCCESS.getValue());
        responseResult.setData(data);
        return responseResult;
    }

    /**
     * <p>成功调用无返回数据</p>
     *
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-01-12 14:58:44
     */
    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    /**
     * <p>调用出错</p>
     *
     * @param resultEnum 错误信息
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-01-12 14:59:08
     */
    public static <T> ResponseResult<T> error(Constant.ResultEnum resultEnum) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.setCode(resultEnum.getCode());
        responseResult.setMessage(resultEnum.getValue());
        return responseResult;
    }

    @Override
    public String toString() {
        return  new GsonBuilder().serializeNulls().create().toJson(this);
    }
}
