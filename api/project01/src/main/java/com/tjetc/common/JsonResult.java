package com.tjetc.common;

import lombok.Getter;

/**
 * 表示JSON操作的结果，包括状态、消息和数据。
 *
 * @param <T> 结果中包含的数据类型。
 */
@Getter
public class JsonResult <T>  {
    // 状态码 0表示成功 大于0表示失败 -1表示未登录或登录过期
    private final Integer status;
    // 错误消息，后端处理失败后 错误消息
    private final String msg;
    // 返回结果集，操作结果正确，返回的数据
    private final T data;

    public JsonResult(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
