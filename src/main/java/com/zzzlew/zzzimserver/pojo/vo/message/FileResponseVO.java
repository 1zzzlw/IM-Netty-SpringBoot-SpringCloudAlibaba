package com.zzzlew.zzzimserver.pojo.vo.message;

import com.zzzlew.zzzimserver.pojo.Message;

import java.io.Serializable;

/**
 * @Auther: zzzlew
 * @Date: 2025/12/9 - 12 - 09 - 18:21
 * @Description: com.zzzlew.zzzimserver.pojo.vo.message
 * @version: 1.0
 */
public class FileResponseVO extends Message implements Serializable {

    @Override
    public int getMessageType() {
        return FileResponseVO;
    }
}
