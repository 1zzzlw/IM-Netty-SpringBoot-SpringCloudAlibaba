package com.zzzlew.zzzimserver.controller;

import com.zzzlew.zzzimserver.pojo.dto.message.FileChunkInfoDTO;
import com.zzzlew.zzzimserver.pojo.dto.message.MessageDTO;
import com.zzzlew.zzzimserver.pojo.vo.message.FileMessageVO;
import com.zzzlew.zzzimserver.pojo.vo.message.MessageVO;
import com.zzzlew.zzzimserver.result.Result;
import com.zzzlew.zzzimserver.server.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 上传文件消息分块
     *
     * @return 消息id
     */
    @Operation(summary = "上传文件消息分块")
    @PostMapping("/uploadChunk")
    public Result<FileMessageVO> uploadFile(@RequestParam("chunkBlob") MultipartFile chunkBlob,
        @ModelAttribute FileChunkInfoDTO fileChunkInfoDTO) {
        log.info("上传文件分块消息的索引：{}，分块哈希值：{}，文件名：{}，是否上传完成：{}", fileChunkInfoDTO.getChunkIndex(),
            fileChunkInfoDTO.getChunkHash(), fileChunkInfoDTO.getFilename(), fileChunkInfoDTO.getIsUploaded());
        messageService.uploadFileChunk(chunkBlob, fileChunkInfoDTO);
        return Result.success();
    }

    /**
     * 获取上传成功的文件分块索引列表
     *
     * @return 上传成功的文件分块索引列表
     */
    @Operation(summary = "获取上传成功的文件分块索引列表")
    @GetMapping("/checkUploaded")
    public Result<List<Integer>> checkUploaded(@RequestParam("filename") String filename) {
        log.info("检查文件分块是否上传完成：{}", filename);
        List<Integer> uploadedChunkIndices = messageService.checkUploaded(filename);
        return Result.success(uploadedChunkIndices);
    }

    /**
     * 合并文件分块
     *
     * @return 合并后的文件消息
     */
    @Operation(summary = "合并文件分块")
    @PostMapping("/merge")
    public Result<FileMessageVO> mergeFile(@RequestParam("filename") String filename,
        @RequestParam("fileType") Integer fileType, @RequestParam("chunkCount") Integer chunkCount) {
        log.info("合并文件分块：{}，文件类型：{}，分块数量：{}", filename, fileType, chunkCount);
        messageService.mergeFile(filename, fileType, chunkCount);
        return Result.success();
    }

}
