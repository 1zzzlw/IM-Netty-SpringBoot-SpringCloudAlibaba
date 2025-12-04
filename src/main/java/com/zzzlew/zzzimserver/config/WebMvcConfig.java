package com.zzzlew.zzzimserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.zzzlew.zzzimserver.utils.RefreshTokenInterceptor;

import jakarta.annotation.Resource;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/12 - 11 - 12 - 17:15
 * @Description: com.zzzlew.zzzimserver.config
 * @version: 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private RefreshTokenInterceptor refreshTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 刷新token拦截器
        registry.addInterceptor(refreshTokenInterceptor).addPathPatterns("/**")
            .excludePathPatterns("/login", "/register", "/verifyCode").order(0);
    }
}
