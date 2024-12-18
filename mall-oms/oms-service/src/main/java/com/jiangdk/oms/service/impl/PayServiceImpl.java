package com.jiangdk.oms.service.impl;

import cn.hutool.http.HttpStatus;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.oms.mapper.OrderMapper;
import com.jiangdk.oms.pojo.entity.Order;
import com.jiangdk.oms.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author: JiangDk
 * @date: 2024/12/17 10:50
 * @description:
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private OrderMapper orderMapper;
    @Value("${alipay.public-key}")
    private String alipayPublicKey;

    /**
     * 支付验签
     *
     * @param params
     */
    @Override
    public void payResult(Map<String, String> params) {
        try{
            // 验签
            AlipaySignature.rsaCheckV1(params,alipayPublicKey,"UTF8","RSA2");
            String appId = params.get("app_id");
            // 交易时间
            String tradeTime = params.get("gmt_payment");
            // 订单号
            String orderSn = params.get("out_trade_no");
            // 订单名称
            String orderName = params.get("subject");
            // 付款金额
            String payAmount = params.get("total_amount");
            // 支付宝交易凭证号
            String tradeNo = params.get("trade_no");
            // 交易状态
            String tradeStatus = params.get("trade_status");
            // 卖家支付宝账号
            String sellerId = params.get("seller_id");
            // 买家支付宝账号
            String buyerLogonId = params.get("buyer_logon_id");
            Order order = orderMapper.selectOne(new LambdaQueryWrapper<Order>().eq(Order::getOrderSn, orderSn));
            if (order!=null && order.getStatus() == 1){
                // 设置为已支付
                order.setStatus(5);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                order.setPayTime(simpleDateFormat.parse(tradeTime));
                orderMapper.updateById(order);
            }

        }catch (AlipayApiException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }

    }

    /**
     * 支付同步回调接口
     * @param params
     */
    @Override
    public void paySuccess(Map<String, String> params) {
        boolean result = false;
        try {
            result = AlipaySignature.rsaCheckV1(
                    params,
                    alipayPublicKey,
                    "UTF8", "RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (!result) {
            throw new BizException(HttpStatus.HTTP_INTERNAL_ERROR, "验签失败");
        }
    }
}
