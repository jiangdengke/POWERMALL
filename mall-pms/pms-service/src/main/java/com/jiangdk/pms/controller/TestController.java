package com.jiangdk.pms.controller;

import com.jiangdk.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: JiangDk
 * @date: 2024/11/29 13:04
 * @description:
 */   
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public Result test(){
        int i = 100/0;
        return Result.success();
    }
}
