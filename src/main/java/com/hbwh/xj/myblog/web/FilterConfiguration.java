package com.hbwh.xj.myblog.web;

import com.hbwh.xj.myblog.web.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class FilterConfiguration {
    /**
     * 对请求API做过滤：若用户没有登录，要重定向到登录页面
     * @return
     */
    @Bean
    public FilterRegistrationBean loginFilterBean() {
        LoginFilter loginFilter = new LoginFilter();

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("loginFilter"); // 过滤器名称
        registrationBean.setFilter(loginFilter); // 注入过滤器
        registrationBean.setOrder(0);           //过滤器顺序
        registrationBean.setUrlPatterns(Arrays.asList("/*")); //拦截规则
        return registrationBean;
    }
}
