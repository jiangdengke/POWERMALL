package com.jiangdk.oms.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author: JiangDk
 * @date: 2024/12/17 10:47
 * @description:
 */
public interface PayService {

    /**
     * 支付成功同步回调接口
     * @param params
     */
    void  paySuccess(@RequestParam Map<String, String> params);

    /**
     * 支付成功后的处理
     * @param params
     */
    void payResult(@RequestParam Map<String, String> params);
}
