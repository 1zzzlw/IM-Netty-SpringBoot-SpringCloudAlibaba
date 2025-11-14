package com.zzzlew.zzzimserver.server.impl;

import com.zzzlew.zzzimserver.mapper.ApplyMapper;
import com.zzzlew.zzzimserver.pojo.dto.friend.ApplyDTO;
import com.zzzlew.zzzimserver.pojo.vo.user.ApplyVO;
import com.zzzlew.zzzimserver.server.ApplyService;
import com.zzzlew.zzzimserver.utils.UserHolder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/14 - 11 - 14 - 22:35
 * @Description: com.zzzlew.zzzimserver.server.impl
 * @version: 1.0
 */
@Slf4j
@Service
public class ApplyServiceImpl implements ApplyService {

    @Resource
    private ApplyMapper applyMapper;

    @Override
    public void sendApply(ApplyDTO applyDTO) {
        // 获得当前登录用户id
        Long userId = UserHolder.getUser().getId();
        log.info("当前登录用户id：{}", userId);
        applyDTO.setFromUserId(userId);
        applyMapper.sendApply(applyDTO);
    }

    @Override
    public List<ApplyVO> getApplyList() {
        // 获得当前登录用户的id
        Long userId = UserHolder.getUser().getId();
        // 根据id查询对应的好友列表
        List<ApplyVO> list = applyMapper.getApplyList(userId);
        log.info("好友申请列表: {}", list);
        return list;
    }

}
