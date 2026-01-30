package com.zzzlew.zzzimserver.mapper;

import com.zzzlew.zzzimserver.pojo.dto.conversation.GroupConversationDTO;
import com.zzzlew.zzzimserver.pojo.dto.user.GroupMemberDTO;
import com.zzzlew.zzzimserver.pojo.entity.GroupConversation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/23 - 11 - 23 - 11:45
 * @Description: com.zzzlew.zzzimserver.mapper
 * @version: 1.0
 */
public interface GroupConversationMapper {

    /**
     * 插入群聊会话
     *
     * @param groupConversationDTO 群聊会话信息
     */
    void insertGroupConversation(GroupConversationDTO groupConversationDTO);

    /**
     * 插入群成员
     *
     * @param groupMemberDTO 群成员信息
     */
    void insertGroupMember(GroupMemberDTO groupMemberDTO);

    /**
     * 更新群聊会话的群成员数量
     *
     * @param conversationId 群聊会话ID
     * @param groupAvatar 群聊会话头像
     */
    void updateGroupMemberCount(String conversationId, String groupAvatar);

    /**
     * 更新群聊会话状态
     *
     * @param conversationId 群聊会话ID
     * @param userId 用户ID
     * @param content 最后一条消息内容
     * @param sendTime 最后一条消息时间
     */
    void updateConversationStatus(String conversationId, Long userId, String content, LocalDateTime sendTime);

    /**
     * 根据群聊会话ID列表查询群聊会话列表
     *
     * @param groupIdList 群聊会话ID列表
     * @return 群聊会话列表
     */
    List<GroupConversation> selectGroupConversationListByConversationIdList(List<String> groupIdList);

}
