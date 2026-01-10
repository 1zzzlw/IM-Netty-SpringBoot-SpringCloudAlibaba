package com.zzzlew.zzzimserver.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Auther: zzzlew
 * @Date: 2026/1/10 - 01 - 10 - 15:50
 * @Description: com.zzzlew.zzzimserver.pojo.entity
 * @version: 1.0
 */
@Data
public class message implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 会话id
     */
    private String conversationId;

    /**
     * 发送人id
     */
    private Long senderId;

    /**
     * 接收人id
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

    /**
     * 发送状态 0：发送中 1：发送成功 2：发送失败
     */
    private Integer sendStatus;

    /**
     * 读取状态 0：未读 1：已读
     */
    private Integer readStatus;

    /**
     * 发送时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    /**
     * 是否撤回 0：否 1：是
     */
    private Integer isRevoked;

    /**
     * 引用消息id
     */
    private Long quoteMsgId;
}
