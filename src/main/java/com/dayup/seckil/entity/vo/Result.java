package com.dayup.seckil.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname Result
 * @Description TODO
 * @Date 2019/12/21 14:59
 * @Created by Yinghao.He
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>{
    private Integer code;
    private String message;
    private T data;

    public static Result failure() {
        Result result=new Result<>();
        result.setResultCode(ResultCode.FAIL);
        return result;
    }
    public static <T> Result failure(T data) {
        Result result=new Result<>();
        result.setResultCode(ResultCode.FAIL);
        result.setData(data);
        return result;
    }
    public static Result failure(ResultCode resultCode) {
        Result result=new Result<>();
        result.setResultCode(resultCode);
        return result;
    }
    public static<T> Result failure(ResultCode resultCode,T data) {
        Result result=new Result<>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
    public static Result success() {
        Result result=new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }
    public static<T> Result success(T data) {
        Result result=new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }
    public static Result success(ResultCode resultCode) {
        Result result=new Result<>();
        result.setResultCode(resultCode);
        return result;
    }
    public static<T> Result success(ResultCode resultCode,T data) {
        Result result=new Result<>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
    public void setResultCode(ResultCode resultCode){
        this.code=resultCode.getCode();
        this.message=resultCode.getMessage();
    }
}
