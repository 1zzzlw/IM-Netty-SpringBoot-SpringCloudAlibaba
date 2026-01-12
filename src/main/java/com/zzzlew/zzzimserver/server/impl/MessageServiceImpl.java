package com.zzzlew.zzzimserver.server.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.zzzlew.zzzimserver.mapper.ConversationMapper;
import com.zzzlew.zzzimserver.mapper.GroupConversationMapper;
import com.zzzlew.zzzimserver.mapper.MessageMapper;
import com.zzzlew.zzzimserver.pojo.dto.message.FileChunkInfoDTO;
import com.zzzlew.zzzimserver.pojo.dto.message.MessageDTO;
import com.zzzlew.zzzimserver.pojo.entity.message;
import com.zzzlew.zzzimserver.pojo.vo.message.MessageVO;
import com.zzzlew.zzzimserver.server.MessageService;
import com.zzzlew.zzzimserver.utils.MinIOFileStorgeUtil;
import com.zzzlew.zzzimserver.utils.UserHolder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.zzzlew.zzzimserver.constant.RedisConstant.FILE_CHUNK_INDEX_KEY;
import static com.zzzlew.zzzimserver.constant.RedisConstant.FILE_CHUNK_INDEX_KEY_TTL;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/15 - 11 - 15 - 23:52
 * @Description: com.zzzlew.zzzimserver.server.impl
 * @version: 1.0
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;
    @Resource
    private ConversationMapper conversationMapper;
    @Resource
    private GroupConversationMapper groupConversationMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MinIOFileStorgeUtil minIOFileStorgeUtil;

    @Override
    public List<MessageVO> initMessageList(String conversationIds) {
        List<String> conversationIdList = List.of(conversationIds.split(","));
        // 查询会话内的消息列表
        List<message> messageList = messageMapper.selectMessageList(conversationIdList);

        List<MessageVO> messageVOList = new ArrayList<>();

        // 转换为消息VO列表
        for (message message : messageList) {
            MessageVO messageVO = BeanUtil.copyProperties(message, MessageVO.class);
            messageVOList.add(messageVO);
        }

        return messageVOList;
    }

    @Transactional
    @Override
    public MessageVO sendMessage(MessageDTO messageDTO) {
        // 获取当前登录用户id
        Long userId = UserHolder.getUser().getId();
        messageDTO.setId(IdUtil.getSnowflakeNextId());
        messageDTO.setSenderId(userId);
        String conversationId = messageDTO.getConversationId();

        MessageVO messageVO = BeanUtil.copyProperties(messageDTO, MessageVO.class);
        LocalDateTime sendTime = LocalDateTime.now();
        messageVO.setSendTime(sendTime);

        // 保存消息到数据库
        messageMapper.saveMessage(messageDTO);

        if (conversationId.startsWith("g_")) {
            // 发送的会话是群聊会话
            // 去掉前缀的操作在前端传递过来的时候就已经完成了
            // String groupId = conversationId.substring(2);
            // 群聊会话id转换为 Long 类型，用来做接收者id
            // Long receiverId = Long.parseLong(groupId);
            // messageDTO.setReceiverId(receiverId);

            // messageDTO.setMsgType(1);
            // messageVO = BeanUtil.copyProperties(messageDTO, MessageVO.class);
            // LocalDateTime sendTime = LocalDateTime.now();
            // messageVO.setSendTime(sendTime);
            //
            // // 保存消息到数据库
            // messageMapper.saveMessage(messageDTO);

            // 修改群聊会话列表中的状态，未读消息数量，最后一条消息时间，最后一条消息内容，以及显示状态。
            groupConversationMapper.updateConversationStatus(conversationId, userId, messageDTO.getContent(), sendTime);

        } else {
            // 发送的会话是私聊会话

            // 这里前端其实已经传递过来的会话id，所以可以不需要重新构建
            // Long receiverId = messageDTO.getReceiverId();

            // String conversationId = userId > receiverId ? userId + "_" + receiverId : receiverId + "_" + userId;

            // conversationId = userId > receiverId ? String.format("%d_%d", userId, receiverId)
            // : String.format("%d_%d", receiverId, userId);

            // messageDTO.setConversationId(conversationId);

            // messageDTO.setMsgType(1);
            //
            // messageVO = BeanUtil.copyProperties(messageDTO, MessageVO.class);
            // LocalDateTime sendTime = LocalDateTime.now();
            // messageVO.setSendTime(sendTime);
            //
            // // 保存消息到数据库
            // messageMapper.saveMessage(messageDTO);

            // 修改私信会话列表中的状态，未读消息数量，最后一条消息时间，最后一条消息内容，以及显示状态。
            Long receiverId = messageDTO.getReceiverId();

            conversationMapper.updateConversationStatus(conversationId, messageDTO.getContent(), sendTime, receiverId);
        }
        return messageVO;
    }

    @Override
    public List<MessageVO> getMessageList(String conversationId) {
        // 获取当前登录用户id
        // Long userId = UserHolder.getUser().getId();
        // 构建 conversationId
        // String conversationId = userId > receiverId ? String.format("%d_%d", userId, receiverId)
        // : String.format("%d_%d", receiverId, userId);
        // 查询数据库中的消息
        List<MessageVO> messageVOList = messageMapper.getMessageList(conversationId);
        // 返回消息列表
        return messageVOList;
    }

    @Override
    public void uploadFileChunk(MultipartFile chunkBlob, FileChunkInfoDTO fileChunkInfoDTO) {
        Integer chunkIndex = fileChunkInfoDTO.getChunkIndex();
        String chunkHash = fileChunkInfoDTO.getChunkHash();

        byte[] bytes = null;
        try {
            bytes = chunkBlob.getBytes();
        } catch (IOException e) {
            log.error("上传文件分块消息失败: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        // 计算接收分片的md5值
        String md5Digest = DigestUtils.md5DigestAsHex(bytes);
        if (!chunkHash.equals(md5Digest)) {
            log.error("md5计算值{}与前端传递的{}不相同", md5Digest, chunkHash);
            // TODO 以后增加自动重试机制
            throw new RuntimeException("md5 校验失败");
        } else {
            log.info("第{}个分片校验成功", chunkIndex);
        }

        // 文件的唯一标识
        String fileHash = fileChunkInfoDTO.getFileHash();

        // 构建minio文件分块路径
        String minioFileChunkPath = "file-chunk/" + fileHash + "/" + chunkIndex;

        // 将文件分块文件存入minio中
        minIOFileStorgeUtil.uploadFileChunk(minioFileChunkPath, chunkBlob);

        String key = FILE_CHUNK_INDEX_KEY + fileHash;
        // 分片文件写入成功之后，将分片索引信息写入redis
        stringRedisTemplate.opsForZSet().add(key, chunkIndex.toString(), chunkIndex);
        // 设置过期时间
        stringRedisTemplate.expire(key, FILE_CHUNK_INDEX_KEY_TTL, TimeUnit.MINUTES);
    }

    @Override
    public List<Integer> checkUploaded(String fileHash) {
        String key = FILE_CHUNK_INDEX_KEY + fileHash;
        Set<String> chunkIndices = stringRedisTemplate.opsForZSet().range(key, 0, -1);
        if (chunkIndices == null || chunkIndices.isEmpty()) {
            // TODO 后期添加从minio查询已上传的分片索引
            return new ArrayList<>();
        }
        // 将 Set<String> 转换为 List<Integer>
        List<Integer> uploadedIndices = chunkIndices.stream().map(Integer::parseInt).collect(Collectors.toList());
        return uploadedIndices;
    }

    @Override
    public void mergeFile(String fileHash, String filename, Integer fileType, Integer chunkCount) {
        // 创建存入minio的文件路径
        String minioFilePath = minIOFileStorgeUtil.buildFilePath(filename);
        // 分块文件所在路径
        String minioFileChunkPath = "file-chunk/" + fileHash + "/";
        // 合并文件分块
        minIOFileStorgeUtil.mergeFileChunks(minioFilePath, minioFileChunkPath, chunkCount);
        // 清除分块文件
        minIOFileStorgeUtil.clearChunkFlies(minioFileChunkPath, chunkCount);
        // 合并成功之后将文件信息存入数据库中
    }

}
