package com.zzzlew.zzzimserver.mapper;

import com.zzzlew.zzzimserver.pojo.dto.friend.ApplyDTO;
import com.zzzlew.zzzimserver.pojo.vo.user.ApplyVO;

import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/14 - 11 - 14 - 22:24
 * @Description: com.zzzlew.zzzimserver.mapper
 * @version: 1.0
 */
public interface ApplyMapper {

    /**
     * 发送好友申请
     * @param applyDTO 好友申请信息
     */
    void sendApply(ApplyDTO applyDTO);

    List<ApplyVO> getApplyList(Long userId);

}
