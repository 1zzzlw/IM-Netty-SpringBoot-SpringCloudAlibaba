package com.zzzlew.zzzimserver.pojo.vo.message;

import com.zzzlew.zzzimserver.pojo.AbstractResponseMessage;
import com.zzzlew.zzzimserver.pojo.Message;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/19 - 11 - 19 - 22:54
 * @Description: com.zzzlew.zzzimserver.pojo.vo.message
 * @version: 1.0
 */
public class GroupChatResponseVO extends AbstractResponseMessage {

    @Override
    public int getMessageType() {
        return Message.GroupChatResponseVO;
    }
}
