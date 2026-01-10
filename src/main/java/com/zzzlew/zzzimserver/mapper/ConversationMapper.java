package com.zzzlew.zzzimserver.mapper;

import com.zzzlew.zzzimserver.pojo.dto.conversation.GroupConversationDTO;
import com.zzzlew.zzzimserver.pojo.entity.Conversation;
import com.zzzlew.zzzimserver.pojo.vo.user.GroupMemberVO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/21 - 11 - 21 - 21:40
 * @Description: com.zzzlew.zzzimserver.mapper
 * @version: 1.0
 */
public interface ConversationMapper {
    /**
     * 根据用户id和会话id列表查询会话列表
     *
     * @param userId 用户id
     * @return 会话列表
     */
    List<Conversation> selectListByUserId(Long userId);

    /**
     * 更新会话状态
     *
     * @param conversationId 会话id
     * @param content 最后一条消息内容
     * @param sendTime 最后一条消息时间
     */
    void updateConversationStatus(String conversationId, String content, LocalDateTime sendTime, Long receiverId);

    /**
     * 插入群聊会话
     *
     * @param groupConversationDTO 群聊会话信息
     */
    void insertGroupConversation(GroupConversationDTO groupConversationDTO);

    /**
     * 更新群聊会话的群成员数量
     *
     * @param conversationId 群聊会话ID
     */
    void updateGroupMemberCount(String conversationId);

    /**
     * 查询群聊成员列表
     *
     * @param conversationId 群聊会话ID
     * @return 群聊成员列表
     */
    List<GroupMemberVO> selectGroupMemberListByConversationId(String conversationId);


    /**
     * 初始化好友会话
     *
     * @param toUserId  接收方用户ID
     * @param fromUserId 发送方用户ID
     */
    void insertConversation(String conversationId, Long toUserId, Long fromUserId, Integer type);

}
