package com.zzzlew.zzzimserver.mapper;

import com.zzzlew.zzzimserver.pojo.dto.message.MessageDTO;
import com.zzzlew.zzzimserver.pojo.entity.message;
import com.zzzlew.zzzimserver.pojo.vo.message.MessageVO;

import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/15 - 11 - 15 - 23:53
 * @Description: com.zzzlew.zzzimserver.mapper
 * @version: 1.0
 */
public interface MessageMapper {

    /**
     * 初始化会话内的消息列表
     * 
     * @param conversationIdList 会话id列表
     * @param quitTime 退出时间
     * @return 消息VO列表
     */
    List<message> selectMessageList(List<String> conversationIdList, String quitTime);

    /**
     * 保存消息
     * 
     * @param messageDTO 消息DTO
     */
    void saveMessage(MessageDTO messageDTO);

    /**
     * 获取会话内的消息列表
     * 
     * @param conversationId 会话id
     * @return 消息VO列表
     */
    List<MessageVO> getMessageList(String conversationId);

}
