package com.zzzlew.zzzimserver.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/7 - 11 - 07 - 0:30
 * @Description: com.zzzlew.zzzimserver.interceptor
 * @version: 1.0
 */
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
