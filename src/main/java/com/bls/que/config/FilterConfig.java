package com.bls.que.config;

import com.bls.que.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: bls-que
 * @package: com.bls.que.config
 * @className: FilterConfig
 * @author: huihui
 * @description: TODO
 * @date: 2024/6/17 10:40
 * @version: 1.0
 */
@Configuration
public class FilterConfig {

    @Autowired
    private UserService userService;

    @Bean
    public FilterRegistrationBean<TokenFilter> loggingFilter(){
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        TokenFilter tokenFilter = new TokenFilter();
        tokenFilter.setUserService(userService);
        registrationBean.setFilter(tokenFilter);
        // 设置应用过滤器的URL模式
        registrationBean.addUrlPatterns("/history/*");
        registrationBean.addUrlPatterns("/page/*");
        registrationBean.addUrlPatterns("/user/*");
        return registrationBean;
    }
}
