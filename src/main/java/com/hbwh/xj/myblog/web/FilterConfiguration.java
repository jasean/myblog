package com.hbwh.xj.myblog.web;

import com.hbwh.xj.myblog.web.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class FilterConfiguration {
    /*************************************
     *
     * Filter
     *
     ************************************/
    /**
     * 对请求API做过滤：若用户没有登录，要重定向到登录页面(转移到拦截器中去实现)
     * @return
     */
    /*@Bean
    public FilterRegistrationBean loginFilterBean() {
        LoginFilter loginFilter = new LoginFilter();

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("loginFilter"); // 过滤器名称
        registrationBean.setFilter(loginFilter); // 注入过滤器
        registrationBean.setOrder(0);           //过滤器顺序
        registrationBean.setUrlPatterns(Arrays.asList("/*")); //拦截规则
        return registrationBean;
    }*/

    /*************************************
     *
     * cors
     *
     ************************************/
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1允许任何域名使用
        corsConfiguration.addAllowedHeader("*"); // 2允许任何头
        corsConfiguration.addAllowedMethod("*"); // 3允许任何方法（post、get等）
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4
        return new CorsFilter(source);
    }
}
