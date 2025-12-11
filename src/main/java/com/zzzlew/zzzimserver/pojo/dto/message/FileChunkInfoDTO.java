package com.zzzlew.zzzimserver.pojo.dto.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zzzlew
 * @Date: 2025/12/10 - 12 - 10 - 22:09
 * @Description: com.zzzlew.zzzimserver.pojo.dto.message
 * @version: 1.0
 */
@Data
public class FileChunkInfoDTO implements Serializable {
    // 分块索引
    private Integer chunkIndex;

    // 分块哈希值md5值
    private String chunkHash;

    // 文件名
    private String filename;

    // 是否上传完成
    private Boolean isUploaded;

}
