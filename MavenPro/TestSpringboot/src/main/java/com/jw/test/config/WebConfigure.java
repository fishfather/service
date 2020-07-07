package com.jw.test.config;

import com.jw.test.interceptor.TestInterceptor;
import com.jw.test.interceptor.TestInterceptor2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login","/index.html", "/js/**","/css/**","/images/**");
        registry.addInterceptor(new TestInterceptor2()).addPathPatterns("/**").excludePathPatterns("/user/**","/js/**","/css/**","/images/**");
    }
}
