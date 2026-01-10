package com.zzzlew.zzzimserver.controller;

import com.zzzlew.zzzimserver.pojo.vo.friend.FriendRelationVO;
import com.zzzlew.zzzimserver.pojo.vo.user.UserSearchVO;
import com.zzzlew.zzzimserver.result.Result;
import com.zzzlew.zzzimserver.server.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/12 - 11 - 12 - 22:51
 * @Description: com.zzzlew.zzzimserver.controller
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/friend")
@Tag(name = "好友接口")
public class FriendController {

    @Resource
    private FriendService friendService;

    /**
     * 获取好友列表 TODO 优化的时候改成分页查询吧
     * 
     * @return 好友列表
     */
    @Operation(summary = "初始化好友列表")
    @GetMapping("/init/list")
    public Result<List<FriendRelationVO>> initFriendList() {
        log.info("初始化好友列表");
        List<FriendRelationVO> friendRelationVOList = friendService.initFriendList();
        return Result.success(friendRelationVOList);
    }

    /**
     * 搜索用户
     * 
     * @param phone 手机号
     * @return 用户搜索vo
     */
    @Operation(summary = "搜索用户")
    @GetMapping("/search")
    public Result<UserSearchVO> search(String phone) {
        log.info("搜索用户 {} 的信息", phone);
        UserSearchVO userSearchVO = friendService.search(phone);
        return Result.success(userSearchVO);
    }

}
