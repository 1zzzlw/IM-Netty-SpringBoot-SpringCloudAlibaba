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
     * 根据前端传来的需要初始化的会话id列表，增量更新并热数据预加载消息列表，当前限额100条
     * 
     * @param conversationIds 会话id列表
     * @param isInit 是否初始化
     * @return 消息列表
     */
    @Operation(summary = "根据前端传来的需要初始化的会话id列表，增量更新并热数据预加载消息列表，当前限额100条")
    @GetMapping("/init/list/{conversationIds}")
    public Result<List<MessageVO>> initMessageList(@PathVariable String conversationIds, @RequestParam Boolean isInit) {
        log.info("需要初始化的会话id列表为：{}", conversationIds);
        List<MessageVO> messageVOList = messageService.initMessageList(conversationIds, isInit);
        return Result.success(messageVOList);
    }

    /**
     * 根据前端最旧消息拉取旧消息到前端数据库中
     *
     * @return 消息列表
     */
    @Operation(summary = "根据前端最旧消息拉取旧消息到前端数据库中")
    @GetMapping("/pull/list/{conversationIds}")
    public Result<List<MessageVO>> pullMessageList(@PathVariable String conversationIds) {
        log.info("需要拉取旧消息的会话id列表为：{}", conversationIds);
        // List<MessageVO> messageVOList = messageService.pullMessageList(conversationIds);
        return Result.success();
    }

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
        log.info("上传文件分块消息的索引：{}，分块哈希值：{}，文件md5值：{}", fileChunkInfoDTO.getChunkIndex(), fileChunkInfoDTO.getChunkHash(),
            fileChunkInfoDTO.getFileHash());
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
    public Result<List<Integer>> checkUploaded(@RequestParam("fileHash") String fileHash) {
        log.info("检查文件分块是否上传完成：{}", fileHash);
        List<Integer> uploadedChunkIndices = messageService.checkUploaded(fileHash);
        return Result.success(uploadedChunkIndices);
    }

    /**
     * 合并文件分块
     *
     * @return 合并后的文件消息
     */
    @Operation(summary = "合并文件分块")
    @PostMapping("/merge")
    public Result<FileMessageVO> mergeFile(@RequestParam("fileHash") String fileHash,
        @RequestParam("fileName") String fileName, @RequestParam("fileType") Integer fileType,
        @RequestParam("chunkCount") Integer chunkCount) {
        log.info("文件Hash：{}，文件名：{}，文件类型：{}，分块数量：{}", fileHash, fileName, fileType, chunkCount);
        messageService.mergeFile(fileHash, fileName, fileType, chunkCount);
        return Result.success();
    }

}
