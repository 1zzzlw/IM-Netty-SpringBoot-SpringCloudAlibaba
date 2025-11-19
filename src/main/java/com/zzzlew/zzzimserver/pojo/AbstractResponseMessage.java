package com.zzzlew.zzzimserver.pojo;

import com.zzzlew.zzzimserver.result.Result;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/19 - 11 - 19 - 22:46
 * @Description: com.zzzlew.zzzimserver.pojo
 * @version: 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractResponseMessage extends Message {
    // 复用Result的响应结构
    private Integer code; // 1成功，0失败（与Result一致）
    private String msg; // 响应消息（成功/失败原因）
    private Object data; // 响应数据（可选）

    // 成功响应构造器（无数据）
    public AbstractResponseMessage() {
        this.code = Result.SUCCESS_CODE; // 建议在Result中定义常量
        this.msg = "操作成功";
    }

    // 成功响应构造器（带数据）
    public AbstractResponseMessage(Object data) {
        this.code = Result.SUCCESS_CODE;
        this.msg = "操作成功";
        this.data = data;
    }

    // 失败响应构造器
    public AbstractResponseMessage(String errorMsg) {
        this.code = Result.ERROR_CODE;
        this.msg = errorMsg;
    }

    // 失败响应构造器（带自定义状态码）
    public AbstractResponseMessage(Integer code, String errorMsg) {
        this.code = code;
        this.msg = errorMsg;
    }

}
