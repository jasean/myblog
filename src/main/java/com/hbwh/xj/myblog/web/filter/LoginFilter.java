package com.hbwh.xj.myblog.web.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LoginFilter implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    private static final List<String> exclusionUrls = Arrays.asList(
            "/users/sessions"
    );

//    public static final String REDIRECT_TAG = "redirect-tag";
//    public static final String REDIRECT_URL = "redirect-url";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestUri = request.getRequestURI();
        logger.info("------------requestUri:{}",requestUri);
        if(exclusionUrls.contains(requestUri)){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //check session
        HttpSession session = request.getSession(false);
        logger.info("------------session:{}", session);
        if(null == session){
            response.sendError(403);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
