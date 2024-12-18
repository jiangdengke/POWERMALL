package com.jiangdk.oms;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: JiangDk
 * @date: 2024/12/17 10:27
 * @description:
 */
@SpringBootTest
public class PayTest {
    @Value("${alipay.private-key}")
    private String privateKey;
    @Value("${alipay.public-key}")
    private String alipayPublicKey;
    @Value("${alipay.server-url}")
    private String serverUrl;
    @Value("${alipay.app-id}")
    private String appId;
    @Test
    @SneakyThrows
    void test(){
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl(serverUrl);
        alipayConfig.setAppId(appId);
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF-8");
        alipayConfig.setSignType("RSA2");
        // 初始化SDK
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);


        // 构造请求参数以调用接口
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();

        // 设置商户订单号
        model.setOutTradeNo("20150320010101001");

        // 设置订单总金额
        model.setTotalAmount("9.00");

        // 设置订单标题
        model.setSubject("测试订单");

        // 设置产品类型编码（电脑网站支付）
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        // 设置PC扫码支付的方式
        model.setQrPayMode("1");

        // 设置商户自定义二维码宽度
        model.setQrcodeWidth(100L);
        request.setBizModel(model);
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        System.out.println(response.getBody());
        if (response.isSuccess()){
            System.out.println("调用成功");
        }else {
            System.out.println("调用失败");
        }
    }
}
