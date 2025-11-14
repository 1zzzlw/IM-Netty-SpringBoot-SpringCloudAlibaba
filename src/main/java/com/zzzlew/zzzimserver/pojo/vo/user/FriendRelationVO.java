package com.zzzlew.zzzimserver.pojo.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/12 - 11 - 12 - 23:01
 * @Description: com.zzzlew.zzzimserver.pojo.vo.user
 * @version: 1.0
 */
@Data
public class FriendRelationVO implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String remark;

    // 关系状态 0：未同意 1：正常好友 2：黑名单
    private Integer relationStatus;
}
