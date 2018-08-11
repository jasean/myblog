package com.hbwh.xj.myblog.util.result;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ResponseResult {

    private ResultCode resultCode;
    private Object data;
    private MultiValueMap<String, String> headers;
    private HttpStatus status = HttpStatus.OK;

    private ResponseResult(){}

    public static ResponseResult get(){
        return new ResponseResult();
    }

    public ResponseResult resultCode(ResultCode resultCode){
        this.resultCode = resultCode;
        return this;
    }

    public ResponseResult data(Object data){
        this.data = data;
        return this;
    }

    public ResponseResult header(String key, String value){
        if (null == headers){
            headers = new LinkedMultiValueMap<>();
        }
        headers.add(key, value);
        return this;
    }

    public ResponseResult status(HttpStatus stat){
        this.status = stat;
        return this;
    }


    public ResponseEntity<Result> build(){
        Result result = new Result();
        result.setResult(resultCode);
        result.setData(data);
        return new ResponseEntity<>(result, headers, status);
    }

}
