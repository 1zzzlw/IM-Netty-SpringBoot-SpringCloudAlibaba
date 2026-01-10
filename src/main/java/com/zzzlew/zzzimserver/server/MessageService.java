package com.zzzlew.zzzimserver.server;

import com.zzzlew.zzzimserver.pojo.dto.message.FileChunkInfoDTO;
import com.zzzlew.zzzimserver.pojo.dto.message.MessageDTO;
import com.zzzlew.zzzimserver.pojo.vo.message.MessageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/15 - 11 - 15 - 23:52
 * @Description: com.zzzlew.zzzimserver.server
 * @version: 1.0
 */
public interface MessageService {

    /**
     * 初始化会话内消息列表
     *
     * @param conversationIds 会话id列表
     * @return 消息列表
     */
    List<MessageVO> initMessageList(String conversationIds);

    /**
     * 发送消息
     *
     * @param messageDTO 消息dto
     * @return 消息vo
     */
    MessageVO sendMessage(MessageDTO messageDTO);

    /**
     * 获取会话内消息列表
     *
     * @param conversationId 会话id
     * @return 消息列表
     */
    List<MessageVO> getMessageList(String conversationId);

    /**
     * 上传文件分片
     *
     * @param chunkBlob 文件分片
     * @param fileChunkInfoDTO 文件分片信息
     */
    void uploadFileChunk(MultipartFile chunkBlob, FileChunkInfoDTO fileChunkInfoDTO);

    /**
     * 检查文件分片是否上传完成
     *
     * @param fileHash 文件哈希值
     * @return 已上传分片索引列表
     */
    List<Integer> checkUploaded(String fileHash);

    /**
     * 合并文件分片
     *
     * @param fileHash 文件哈希值
     * @param filename 文件名
     * @param fileType 文件类型
     * @param chunkCount 分片数量
     */
    void mergeFile(String fileHash, String filename, Integer fileType, Integer chunkCount);
}
