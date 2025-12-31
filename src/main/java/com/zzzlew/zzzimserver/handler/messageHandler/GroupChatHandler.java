package com.zzzlew.zzzimserver.handler.messageHandler;

import cn.hutool.core.bean.BeanUtil;
import com.zzzlew.zzzimserver.pojo.dto.message.GroupChatRequestDTO;
import com.zzzlew.zzzimserver.pojo.vo.message.GroupChatResponseVO;
import com.zzzlew.zzzimserver.utils.ChannelManageUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/19 - 11 - 19 - 21:25
 * @Description: com.zzzlew.zzzimserver.handler.messageHandler
 * @version: 1.0
 */
@Slf4j
@ChannelHandler.Sharable
public class GroupChatHandler extends SimpleChannelInboundHandler<GroupChatRequestDTO> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestDTO groupChatRequestDTO) throws Exception {
        // 处理群聊消息
        log.info("收到群聊消息: {}", groupChatRequestDTO);
        // 获得当前登录用户id
        Long userId = ChannelManageUtil.getUser(ctx.channel()).getId();
        groupChatRequestDTO.setSenderId(userId);
        // 设置消息类型为1消息
        groupChatRequestDTO.setMsgType(1);
        // 获得接收者id列表
        List<Long> receiverIds = groupChatRequestDTO.getReceiverIds();
        LocalDateTime sendTime = LocalDateTime.now();
        // 遍历接收者id列表
        for (Long receiverId : receiverIds) {
            if (receiverId.equals(userId)) {
                continue;
            }
            // 拿到接收者的channel
            Channel channel = ChannelManageUtil.getChannel(receiverId);
            if (channel != null) {
                GroupChatResponseVO groupChatResponseVO =
                    BeanUtil.copyProperties(groupChatRequestDTO, GroupChatResponseVO.class);
                groupChatResponseVO.setReceiverId(receiverId);
                groupChatResponseVO.setSendTime(sendTime);
                // 发送消息
                channel.writeAndFlush(groupChatResponseVO);
                log.info("向用户{}发送群聊消息: {}", receiverId, groupChatResponseVO);
            } else {
                log.warn("用户{}的channel不存在", receiverId);
            }
        }
    }

}
