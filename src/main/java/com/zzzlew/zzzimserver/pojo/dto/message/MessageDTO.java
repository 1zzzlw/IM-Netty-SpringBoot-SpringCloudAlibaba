package com.zzzlew.zzzimserver.pojo.dto.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/15 - 11 - 15 - 23:50
 * @Description: com.zzzlew.zzzimserver.pojo.dto.message
 * @version: 1.0
 */
@Data
public class MessageDTO implements Serializable {

    /**
     * 消息id
     */
    private Long id;

    /**
     * 会话id
     */
    private String conversationId;

    /**
     * 发送者id
     */
    private Long senderId;

    /**
     * 接收者id
     */
    private Long receiverId;

    /**
     * 消息类型 1：文本消息 2：图片消息 3：语音消息 4：视频消息 5：文件消息
     */
    private Integer msgType;

    /**
     * 消息内容
     */
    private String content;

}
