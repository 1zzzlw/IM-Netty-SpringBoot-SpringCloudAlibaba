package com.zzzlew.zzzimserver.mapper;

import com.zzzlew.zzzimserver.pojo.vo.user.FriendRelationVO;

import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/12 - 11 - 12 - 23:25
 * @Description: com.zzzlew.zzzimserver.mapper
 * @version: 1.0
 */
public interface FriendMapper {
    /**
     * 查询用户好友列表
     * 
     * @param userId 用户ID
     * @return 好友列表
     */
    List<FriendRelationVO> selectFriendList(Long userId);

}
