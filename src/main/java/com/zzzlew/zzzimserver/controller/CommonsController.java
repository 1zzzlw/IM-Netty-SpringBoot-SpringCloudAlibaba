package com.zzzlew.zzzimserver.controller;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/17 - 11 - 17 - 20:07
 * @Description: com.zzzlew.zzzimserver.controller
 * @version: 1.0
 */

import org.springframework.web.bind.annotation.*;
import com.zzzlew.zzzimserver.result.Result;
import com.zzzlew.zzzimserver.server.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用控制器类
 */
@Slf4j
@RestController
@RequestMapping("/commons")
@Tag(name = "通用接口")
public class CommonsController {

    @Resource
    private UserService userService;

    @Operation(summary = "刷新token")
    @PostMapping("/refreshToken/{userId}")
    public Result<String> refreshToken(@PathVariable("userId") Long userId, HttpServletResponse response) {
        log.info("用户id为：{} 开始刷新AccessToken", userId);
        userService.refreshToken(userId, response);
        return Result.success();
    }

}
