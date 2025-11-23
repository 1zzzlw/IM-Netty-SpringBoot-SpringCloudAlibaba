package com.zzzlew.zzzimserver.pojo.vo.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzzlew.zzzimserver.pojo.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/19 - 11 - 19 - 22:54
 * @Description: com.zzzlew.zzzimserver.pojo.vo.message
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupChatResponseVO extends Message implements Serializable {

    /**
     * 群聊ID
     */
    private String conversationId;

    /**
     * 发送者ID
     */
    private Long senderId;

    /**
     * 接收者ID
     */
    private Long receiverId;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 消息内容
     */
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @Override
    public int getMessageType() {
        return Message.GroupChatResponseVO;
    }
}
