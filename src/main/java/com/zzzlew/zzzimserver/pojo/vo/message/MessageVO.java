package com.zzzlew.zzzimserver.pojo.vo.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/16 - 11 - 16 - 13:07
 * @Description: com.zzzlew.zzzimserver.pojo.vo.message
 * @version: 1.0
 */
@Data
public class MessageVO implements Serializable {

    /**
     * 消息id
     */
    @JsonSerialize(using = ToStringSerializer.class)
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
     * 消息类型
     */
    private Integer msgType;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 发送状态
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

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 本地url
     */
    private String localPath;

    /**
     * 远程url
     */
    private String remoteUrl;

    /**
     * 下载状态 0：未下载 1：已下载
     */
    private Integer downloadStatus;

    /**
     * 接收时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime;
}
