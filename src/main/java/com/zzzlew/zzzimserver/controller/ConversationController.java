package com.zzzlew.zzzimserver.controller;

import com.zzzlew.zzzimserver.pojo.vo.conversation.ConversationVO;
import com.zzzlew.zzzimserver.result.Result;
import com.zzzlew.zzzimserver.server.ConversationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/21 - 11 - 21 - 21:00
 * @Description: com.zzzlew.zzzimserver.controller
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Resource
    private ConversationService conversationService;

    /**
     * 获取会话列表
     * 
     * @return 会话列表
     */
    @GetMapping("/list/{conversationId}")
    public Result<List<ConversationVO>> getConversationList(@PathVariable String conversationId) {
        List<String> conversationIdList = Arrays.asList(conversationId.split(","));
        log.info("会话ID列表：{}", conversationIdList);
        List<ConversationVO> conversationVOList = conversationService.getConversationList(conversationIdList);
        return Result.success(conversationVOList);
    }

}
