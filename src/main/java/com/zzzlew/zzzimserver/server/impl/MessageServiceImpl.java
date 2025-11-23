package com.zzzlew.zzzimserver.server.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zzzlew.zzzimserver.mapper.ConversationMapper;
import com.zzzlew.zzzimserver.mapper.GroupConversationMapper;
import com.zzzlew.zzzimserver.mapper.MessageMapper;
import com.zzzlew.zzzimserver.pojo.dto.message.MessageDTO;
import com.zzzlew.zzzimserver.pojo.vo.message.MessageVO;
import com.zzzlew.zzzimserver.server.MessageService;
import com.zzzlew.zzzimserver.utils.UserHolder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/15 - 11 - 15 - 23:52
 * @Description: com.zzzlew.zzzimserver.server.impl
 * @version: 1.0
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;
    @Resource
    private ConversationMapper conversationMapper;
    @Resource
    private GroupConversationMapper groupConversationMapper;

    @Transactional
    @Override
    public MessageVO sendMessage(MessageDTO messageDTO) {
        // 获取当前登录用户id
        Long userId = UserHolder.getUser().getId();
        messageDTO.setSenderId(userId);
        String conversationId = messageDTO.getConversationId();

        messageDTO.setMsgType(1);
        MessageVO messageVO = BeanUtil.copyProperties(messageDTO, MessageVO.class);
        LocalDateTime sendTime = LocalDateTime.now();
        messageVO.setSendTime(sendTime);

        // 保存消息到数据库
        messageMapper.saveMessage(messageDTO);

        if (conversationId.startsWith("g_")) {
            // 发送的会话是群聊会话
            // 去掉前缀的操作在前端传递过来的时候就已经完成了
            // String groupId = conversationId.substring(2);
            // 群聊会话id转换为 Long 类型，用来做接收者id
            // Long receiverId = Long.parseLong(groupId);
            // messageDTO.setReceiverId(receiverId);

            // messageDTO.setMsgType(1);
            // messageVO = BeanUtil.copyProperties(messageDTO, MessageVO.class);
            // LocalDateTime sendTime = LocalDateTime.now();
            // messageVO.setSendTime(sendTime);
            //
            // // 保存消息到数据库
            // messageMapper.saveMessage(messageDTO);

            // 修改群聊会话列表中的状态，未读消息数量，最后一条消息时间，最后一条消息内容，以及显示状态。
            groupConversationMapper.updateConversationStatus(conversationId, userId, messageDTO.getContent(), sendTime);

        } else {
            // 发送的会话是私聊会话

            // 这里前端其实已经传递过来的会话id，所以可以不需要重新构建
            // Long receiverId = messageDTO.getReceiverId();

            // String conversationId = userId > receiverId ? userId + "_" + receiverId : receiverId + "_" + userId;

            // conversationId = userId > receiverId ? String.format("%d_%d", userId, receiverId)
            // : String.format("%d_%d", receiverId, userId);

            // messageDTO.setConversationId(conversationId);

            // messageDTO.setMsgType(1);
            //
            // messageVO = BeanUtil.copyProperties(messageDTO, MessageVO.class);
            // LocalDateTime sendTime = LocalDateTime.now();
            // messageVO.setSendTime(sendTime);
            //
            // // 保存消息到数据库
            // messageMapper.saveMessage(messageDTO);

            // 修改私信会话列表中的状态，未读消息数量，最后一条消息时间，最后一条消息内容，以及显示状态。
            Long receiverId = messageDTO.getReceiverId();

            conversationMapper.updateConversationStatus(conversationId, messageDTO.getContent(), sendTime, receiverId);
        }
        return messageVO;
    }

    @Override
    public List<MessageVO> getMessageList(String conversationId) {
        // 获取当前登录用户id
        // Long userId = UserHolder.getUser().getId();
        // 构建 conversationId
        // String conversationId = userId > receiverId ? String.format("%d_%d", userId, receiverId)
        // : String.format("%d_%d", receiverId, userId);
        // 查询数据库中的消息
        List<MessageVO> messageVOList = messageMapper.getMessageList(conversationId);
        // 返回消息列表
        return messageVOList;
    }
}
