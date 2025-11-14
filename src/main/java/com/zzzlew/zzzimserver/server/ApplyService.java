package com.zzzlew.zzzimserver.server;

import com.zzzlew.zzzimserver.pojo.dto.friend.ApplyDTO;
import com.zzzlew.zzzimserver.pojo.vo.user.ApplyVO;

import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/14 - 11 - 14 - 22:34
 * @Description: com.zzzlew.zzzimserver.server
 * @version: 1.0
 */
public interface ApplyService {

    /**
     * 发送好友申请
     * 
     * @param applyDTO 好友申请信息
     */
    void sendApply(ApplyDTO applyDTO);

    /**
     * 获取好友申请列表
     *
     * @return 好友申请列表
     */
    List<ApplyVO> getApplyList();

}
