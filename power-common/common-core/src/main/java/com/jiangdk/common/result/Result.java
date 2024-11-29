package com.jiangdk.common.result;

import cn.hutool.http.HttpStatus;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: JiangDk
 * @date: 2024/11/29 11:44
 * @description:
 */
@Data
@Accessors(chain = true) // 启用链式编程
public class Result<T> {
    private int code; // 状态码
    private String msg; // 响应消息
    private T data; // 响应数据

    public Result() {}

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 判断请求是否失败
    public boolean isError() {
        return this.code != 200;
    }

    // 请求成功
    public static Result success() {
        return new Result(HttpStatus.HTTP_OK, "请求成功");
    }

    // 请求成功
    public static <T> Result<T> success(T data) {
        return Result.success().setData(data);
    }

    // 请求成功
    public static <T> Result<T> success(String msg, T data) {
        return Result.success(data).setMsg(msg);
    }

    // 请求失败
    public static Result error(int code, String msg) {
        return new Result(code, msg);
    }
}
