package com.hbwh.xj.myblog.util.result;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class Result implements Serializable {

    private static final long serialVersionUID = -3948389268046368059L;

    private Integer code;

    private String msg;

    private Object data;

    public Result() {}

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success() {
        return success(null);
    }

    public static Result success(String message){
        return success(message, null);
    }

    public static Result success(Object data) {
        return success(null, data);
    }

    public static Result success(String message, Object data){
        Result result = new Result();
        result.setResult(ResultCode.SUCCESS, message);
        result.setData(data);
        return result;
    }

    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResult(resultCode);
        return result;
    }

    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResult(resultCode);
        result.setData(data);
        return result;
    }


    public void setResult(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }

    public void setResult(int code, String message){
        this.code = code;
        this.msg = message;
    }

    public void setResult(ResultCode code, String message){
        if(StringUtils.isBlank(message)){
            setResult(code);
        }else{
            setResult(code.code(), code.message() + "[" + message + "]");
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
