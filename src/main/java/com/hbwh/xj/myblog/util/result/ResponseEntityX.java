package com.hbwh.xj.myblog.util.result;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityX extends ResponseEntity<Result> {
    public ResponseEntityX(Result body, HttpStatus status) {
        super(body, status);
    }

    public ResponseEntityX(Result body){
        super(body, HttpStatus.OK);
    }

    public ResponseEntityX(){
        super(Result.success(), HttpStatus.OK);
    }
}
