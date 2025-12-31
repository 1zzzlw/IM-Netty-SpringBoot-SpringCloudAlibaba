package com.zzzlew.zzzimserver.mapper;

import com.zzzlew.zzzimserver.pojo.entity.UserAuth;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/6 - 11 - 06 - 23:09
 * @Description: com.zzzlew.zzzimserver.mapper
 * @version: 1.0
 */
public interface UserMapper {
    /**
     * 根据账号查询用户
     * @param account 账号
     * @return 用户实体类
     */
    @Select("select * from user_auth where account = #{account}")
    UserAuth getByAccount(String account);

    /**
     * 插入用户
     * @param
     */
    void insertUserAuth(UserAuth userAuth);

}
