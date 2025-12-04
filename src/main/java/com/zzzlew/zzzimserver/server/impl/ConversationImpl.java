package com.zzzlew.zzzimserver.server.impl;

import com.zzzlew.zzzimserver.mapper.ConversationMapper;
import com.zzzlew.zzzimserver.pojo.vo.conversation.ConversationVO;
import com.zzzlew.zzzimserver.pojo.vo.conversation.GroupConversationVO;
import com.zzzlew.zzzimserver.pojo.vo.user.GroupMemberVO;
import com.zzzlew.zzzimserver.server.ConversationService;
import com.zzzlew.zzzimserver.utils.UserHolder;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/21 - 11 - 21 - 21:40
 * @Description: com.zzzlew.zzzimserver.server.impl
 * @version: 1.0
 */
@Service
public class ConversationImpl implements ConversationService {

    @Resource
    private ConversationMapper conversationMapper;

    /**
     * 获取会话列表
     *
     * @param conversationIdList 会话ID列表
     * @return 会话列表
     */
    @Override
    public List<ConversationVO> getConversationList(List<String> conversationIdList) {
        // 获得当前登录用户id
        Long userId = UserHolder.getUser().getId();
        // 根据用户id和会话id查询登录用户的会话列表
        List<ConversationVO> conversationVOList =
            conversationMapper.selectListByUserIdAndConversationIdList(userId, conversationIdList);
        return conversationVOList;
    }

    @Override
    public List<GroupConversationVO> getGroupConversationList() {
        // 获得当前登录用户id
        Long userId = UserHolder.getUser().getId();
        // 根据用户id和会话id查询登录用户的会话列表
        List<GroupConversationVO> groupConversationVOList =
            conversationMapper.selectGroupListByUserIdAndConversationId(userId);
        return groupConversationVOList;
    }

    @Override
    public List<GroupMemberVO> getGroupMemberList(String conversationId) {
        // 根据用户id和会话id查询登录用户的会话列表
        List<GroupMemberVO> groupMemberVOList =
            conversationMapper.selectGroupMemberListByConversationId(conversationId);
        return groupMemberVOList;
    }

    @Override
    public void clearConversationUnreadCounts(String conversationId) {
        // TODO 清除会话中未读消息计数
    }

}
