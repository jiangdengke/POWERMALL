package com.jiangdk.common.exception;

import lombok.Getter;

/**
 * @author: JiangDk
 * @date: 2024/11/29 11:53
 * @description:
 */

public class BizException extends RuntimeException{
    @Getter
    private int code;
    public BizException(int code,String msg){
        super(msg);
        this.code = code;
    }
}
