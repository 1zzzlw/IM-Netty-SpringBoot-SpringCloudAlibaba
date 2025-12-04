package com.zzzlew.zzzimserver.constant;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/9 - 11 - 09 - 22:00
 * @Description: com.zzzlew.zzzimserver.constant
 * @version: 1.0
 */
public class RedisConstant {

    public static final String LOGIN_USER_TOKEN_LIST_KEY = "login:user:tokenList:";
    public static final Long LOGIN_USER_TOKEN_LIST_KEY_TTL = 36000L;

    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final Long LOGIN_CODE_TTL = 2L;

    public static final String LOGIN_USER_KEY = "login:user:Info:";
    public static final Long LOGIN_USER_KEY_TTL = 36000L;

    public static final String REGISTER_CODE_KEY = "register:code:";
    public static final Long REGISTER_CODE_TTL = 2L;

    public static final String USER_FRIEND_LIST_KEY = "user:friend:list:";
    public static final Long USER_FRIEND_LIST_KEY_TTL = 2L;

    public static final String USER_ONLINE_STATUS_KEY = "user:online:status:";
    // 270秒过期时间，因为用户在270秒内没有操作，就可以判断为用户不在线，对应心跳时间90秒
    public static final Long USER_ONLINE_STATUS_KEY_TTL = 270L;
}
