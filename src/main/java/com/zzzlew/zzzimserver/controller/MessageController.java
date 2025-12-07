package com.zzzlew.zzzimserver.controller;

import com.zzzlew.zzzimserver.pojo.dto.message.MessageDTO;
import com.zzzlew.zzzimserver.pojo.vo.message.MessageVO;
import com.zzzlew.zzzimserver.result.Result;
import com.zzzlew.zzzimserver.server.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/15 - 11 - 15 - 22:41
 * @Description: com.zzzlew.zzzimserver.controller
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/message")
@Tag(name = "消息模块")
public class MessageController {

    @Resource
    private MessageService messageService;

    /**
     * 发送消息
     * 
     * @return 消息id
     */
    @Operation(summary = "发送消息")
    @PostMapping("/send")
    public Result<MessageVO> sendMessage(@RequestBody MessageDTO messageDTO) {
        log.info("发送消息：{}", messageDTO);
        MessageVO messageVO = messageService.sendMessage(messageDTO);
        return Result.success(messageVO);
    }

    /**
     * 获取消息列表
     *
     * @return 消息列表
     */
    @Operation(summary = "获取消息列表")
    @GetMapping("/list")
    public Result<List<MessageVO>> getMessageList(@RequestParam String conversationId) {
        log.info("对话id为：{}", conversationId);
        List<MessageVO> messageVOList = messageService.getMessageList(conversationId);
        log.info("消息列表：{}", messageVOList);
        return Result.success(messageVOList);
    }

}
