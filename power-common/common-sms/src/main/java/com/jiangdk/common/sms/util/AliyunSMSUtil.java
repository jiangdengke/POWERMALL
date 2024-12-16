package com.jiangdk.common.sms.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: JiangDk
 * @date: 2024/12/16 14:09
 * @description:
 */
@Component
@Slf4j
public class AliyunSMSUtil {
    @Autowired
    private IAcsClient iAcsClient;

    /**
     * 发送短信验证码
     *
     * @param mobile       手机号
     * @param code         验证码
     * @param signName     短信签名
     * @param templateCode 短信模板CODE
     */
    public void sendVerificationCode(String mobile, String code, String signName, String templateCode) {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumbers(mobile);
        request.setTemplateParam(JSONUtil.toJsonStr(MapUtil.builder("code", code).build()));
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        try {
            SendSmsResponse response = iAcsClient.getAcsResponse(request);
            log.info(JSONUtil.toJsonStr(response));
        } catch (ClientException e) {
            e.printStackTrace();
            throw new BizException(HttpStatus.HTTP_INTERNAL_ERROR,e.getErrCode()+":"+e.getErrMsg());
        }
    }
}
