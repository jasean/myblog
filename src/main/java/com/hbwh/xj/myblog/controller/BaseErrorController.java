package com.hbwh.xj.myblog.controller;

import com.hbwh.xj.myblog.util.result.ResponseResult;
import com.hbwh.xj.myblog.util.result.Result;
import com.hbwh.xj.myblog.util.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/error")
public class BaseErrorController  {

    @Autowired
    private ErrorAttributes errorAttributes;


    @GetMapping
    public void error(HttpServletRequest request, HttpServletResponse response){
        WebRequest requestAttributes = new ServletWebRequest(request);
        Map<String, Object> errors = errorAttributes
                .getErrorAttributes(requestAttributes, true);
        try {
            response.getWriter().print(errors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
