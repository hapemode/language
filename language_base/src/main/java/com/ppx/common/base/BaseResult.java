package com.ppx.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: hape
 * @Date: 2022/11/15 15:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> {
    private String code;
    private String message;
    private T data;

    public static BaseResult getSuccessResult() {
        BaseResult<Object> result = new BaseResult<>();
        result.setCodeAndMessage(ResultEnum.SUCCESS, null);
        return result;
    }

    public static BaseResult getFailResult() {
        BaseResult<Object> result = new BaseResult<>();
        result.setCodeAndMessage(ResultEnum.FAIL, null);
        return result;
    }

    public void setCodeAndMessage(ResultEnum resultEnum, T data) {
        code = resultEnum.getCode();
        message = resultEnum.getMessage();
        this.data = data;
    }

    public BaseResult<T> setCodeAndMessage(String code, String message, T data) {
        BaseResult<T> result = new BaseResult<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
