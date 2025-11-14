package com.zzzlew.zzzimserver.controller;

import com.zzzlew.zzzimserver.pojo.dto.friend.ApplyDTO;
import com.zzzlew.zzzimserver.pojo.vo.user.ApplyVO;
import com.zzzlew.zzzimserver.result.Result;
import com.zzzlew.zzzimserver.server.ApplyService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/14 - 11 - 14 - 22:33
 * @Description: com.zzzlew.zzzimserver.controller
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Resource
    private ApplyService applyService;

    @PostMapping("/send")
    public Result<Object> sendApply(@RequestBody ApplyDTO applyDTO) {
        log.info("添加好友，申请信息：{}", applyDTO);
        applyService.sendApply(applyDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<ApplyVO>> getApplyList() {
        List<ApplyVO> applyList = applyService.getApplyList();
        return Result.success(applyList);
    }

    @PostMapping("/agree")
    public Result<Object> agreeApply(@RequestBody ApplyDTO applyDTO) {
        log.info("同意好友申请，申请信息：{}", applyDTO);
        // applyService.agreeApply(applyDTO);
        return Result.success();
    }

}
