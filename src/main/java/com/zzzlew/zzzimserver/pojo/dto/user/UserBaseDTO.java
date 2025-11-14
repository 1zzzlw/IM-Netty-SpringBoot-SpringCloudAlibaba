package com.zzzlew.zzzimserver.pojo.dto.user;

import lombok.Data;

/**
 * 用户基础信息DTO
 */
@Data
public class UserBaseDTO {
    private Long id;

    private String username;

    private String account;

    private String password;

    private String avatar;

}
