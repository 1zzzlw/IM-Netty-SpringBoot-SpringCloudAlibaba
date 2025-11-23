package com.zzzlew.zzzimserver.mapper;

import java.time.LocalDateTime;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/23 - 11 - 23 - 11:45
 * @Description: com.zzzlew.zzzimserver.mapper
 * @version: 1.0
 */
public interface GroupConversationMapper {

    void updateConversationStatus(String conversationId, Long userId, String content, LocalDateTime sendTime);

}
