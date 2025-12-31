package com.zzzlew.zzzimserver.server;

import com.zzzlew.zzzimserver.pojo.dto.user.UserLoginDTO;
import com.zzzlew.zzzimserver.pojo.dto.user.UserRegisterDTO;
import com.zzzlew.zzzimserver.pojo.vo.user.UserInfoVO;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/6 - 11 - 06 - 23:08
 * @Description: com.zzzlew.zzzimserver.server
 * @version: 1.0
 */
public interface UserService {

    UserInfoVO login(UserLoginDTO userLoginDTO, HttpServletResponse response);

    void createCode(HttpServletResponse response);

    Long register(UserRegisterDTO userRegisterDTO, HttpServletResponse response);

    String createPhoneCode(String phone);

    void pendingLogin(String token, Long userId);

    void refreshToken(Long userId, HttpServletResponse response);

}
