package com.zzzlew.zzzimserver.controller;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/17 - 11 - 17 - 20:07
 * @Description: com.zzzlew.zzzimserver.controller
 * @version: 1.0
 */

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzzlew.zzzimserver.result.Result;
import com.zzzlew.zzzimserver.server.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;

/**
 * 通用控制器类
 */
@RestController
@RequestMapping("/commons")
@Tag(name = "通用接口")
public class CommonsController {

    @Resource
    private UserService userService;

    @Operation(summary = "刷新token")
    @PostMapping("/refreshToken")
    public Result<String> refreshToken() {
        String token = userService.refreshToken();
        return Result.success(token);
    }

}
