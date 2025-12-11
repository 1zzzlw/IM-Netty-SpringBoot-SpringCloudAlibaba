package com.zzzlew.zzzimserver.pojo.dto.message;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @Auther: zzzlew
 * @Date: 2025/12/10 - 12 - 10 - 19:14
 * @Description: com.zzzlew.zzzimserver.pojo.dto.message
 * @version: 1.0
 */
@Data
public class FileMessageDTO implements Serializable {
    // 分块文件的二进制数据
    private MultipartFile chunkBlob;

    // 文件分块信息
    private FileChunkInfoDTO fileChunkInfoDTO;
}
