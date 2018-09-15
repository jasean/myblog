package com.hbwh.xj.myblog.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    //登陆校验排除路径
    public static final List<String> excludePaths = Arrays.asList(
            "/users/sessions","/users"
    );

    //登陆校验排除请求方法类型
    public static final List<String> excludeMethod = Arrays.asList(
            "OPTIONS"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String method = request.getMethod().toUpperCase();
        logger.info("-----------------request method: {},url: {}", method, request.getRequestURI());
        if(!excludeMethod.contains(method)){
            //check session
            HttpSession session = request.getSession(false);
            if(session == null){
                response.sendError(401);
                return false;
            }
        }

        return true;
    }
}
