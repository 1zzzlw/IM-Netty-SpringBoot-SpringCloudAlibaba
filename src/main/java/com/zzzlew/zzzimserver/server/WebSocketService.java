package com.zzzlew.zzzimserver.server;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.zzzlew.zzzimserver.pojo.vo.message.MessageVO;
import com.zzzlew.zzzimserver.utils.ChannelManageUtil;

import cn.hutool.json.JSONUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/18 - 11 - 18 - 0:07
 * @Description: com.zzzlew.zzzimserver.server
 * @version: 1.0
 */
@Slf4j
@Service
public class WebSocketService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void offline(Channel channel) {

    }

    public void online(Channel channel) {
        // TODO 用户上线提醒，之后完成吧
        // 获得用户id
        Long userId = ChannelManageUtil.getUser(channel).getId();

    }
}
