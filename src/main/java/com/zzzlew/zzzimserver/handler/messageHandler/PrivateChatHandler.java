package com.zzzlew.zzzimserver.handler.messageHandler;

import com.zzzlew.zzzimserver.pojo.dto.message.PrivateChatRequestDTO;
import com.zzzlew.zzzimserver.pojo.vo.message.PrivateChatResponseVO;
import com.zzzlew.zzzimserver.utils.ChannelManageUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/19 - 11 - 19 - 21:25
 * @Description: com.zzzlew.zzzimserver.handler.messageHandler
 * @version: 1.0
 */
@Slf4j
@ChannelHandler.Sharable
public class PrivateChatHandler extends SimpleChannelInboundHandler<PrivateChatRequestDTO> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PrivateChatRequestDTO privateChatRequestDTO)
        throws Exception {
        // 处理私聊消息
        log.info("收到私聊消息：{}", privateChatRequestDTO);
        // 获得当前登录用户id
        Long userId = ChannelManageUtil.getUser(ctx.channel()).getId();
        // 获得接收者id
        Long receiverId = privateChatRequestDTO.getReceiverId();
        // 搭建 conversationId
        String conversationId = userId > receiverId ? String.format("%d_%d", userId, receiverId)
            : String.format("%d_%d", receiverId, userId);
        // 完善 privateChatRequestDTO
        privateChatRequestDTO.setConversationId(conversationId);
        privateChatRequestDTO.setSenderId(userId);
        privateChatRequestDTO.setMsgType(1);
        // 获取接收者的channel
        Channel channel = ChannelManageUtil.getChannel(receiverId);
        if (channel != null) {
            // 发送消息
            channel.writeAndFlush(new PrivateChatResponseVO(privateChatRequestDTO));
            log.info("响应消息已写入发送队列");
        } else {
            // 接收者不在线
            log.info("接收者不在线");
        }
    }

}
