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
            // 排除登录、注册、验证码、文档页面、Swagger UI、OpenAPI JSON、静态资源、Swagger 资源
            .excludePathPatterns("/login", // 登录接口
                "/register", // 注册接口
                "/verifyCode", // 验证码接口
                "/swagger-ui/**", // Swagger 原生 UI
                "/v3/api-docs/**", // OpenAPI JSON
                "/webjars/**", // 静态资源
                "/doc.html/**", // 文档页面
                "/doc.html", "/error/**", "/swagger-resources/**" // Swagger 资源
            ).order(0);
    }
}
