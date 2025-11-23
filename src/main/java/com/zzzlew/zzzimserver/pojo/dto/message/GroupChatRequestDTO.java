package com.zzzlew.zzzimserver.pojo.dto.message;

import com.zzzlew.zzzimserver.pojo.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/19 - 11 - 19 - 16:16
 * @Description: com.zzzlew.zzzimserver.pojo.dto.message
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupChatRequestDTO extends Message implements Serializable {

    /**
     * 群聊会话id
     */
    private String conversationId;

    /**
     * 发送者id
     */
    private Long senderId;

    /**
     * 接收者id列表
     */
    private List<Long> receiverIds;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 消息内容
     */
    private String content;

    @Override
    public int getMessageType() {
        return GroupChatRequestDTO;
    }
}
