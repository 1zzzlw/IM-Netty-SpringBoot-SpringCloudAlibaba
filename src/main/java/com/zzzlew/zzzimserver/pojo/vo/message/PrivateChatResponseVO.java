package com.zzzlew.zzzimserver.pojo.vo.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzzlew.zzzimserver.pojo.AbstractResponseMessage;
import com.zzzlew.zzzimserver.pojo.dto.message.PrivateChatRequestDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/19 - 11 - 19 - 22:46
 * @Description: com.zzzlew.zzzimserver.pojo.vo.message
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PrivateChatResponseVO extends AbstractResponseMessage {

    private String conversationId;

    private Long senderId;

    private Long receiverId;

    /**
     * 消息类型 1：文本消息 2：图片消息 3：语音消息 4：视频消息 5：文件消息
     */
    private Integer msgType;

    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    // 构造器：成功响应（携带业务数据）
    public PrivateChatResponseVO(PrivateChatRequestDTO request) {
        // 调用父类带数据的构造器
        super(request);
        // 复制请求中的业务字段到响应
        this.conversationId = request.getConversationId();
        this.senderId = request.getSenderId();
        this.receiverId = request.getReceiverId();
        this.msgType = request.getMsgType();
        this.content = request.getContent();
    }

    // 构造器：失败响应
    public PrivateChatResponseVO(String errorMsg) {
        // 调用父类失败构造器
        super(errorMsg);
    }

    @Override
    public int getMessageType() {
        return PrivateChatResponseVO;
    }
}
