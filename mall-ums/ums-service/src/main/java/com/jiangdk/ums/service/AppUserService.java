package com.jiangdk.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiangdk.ums.pojo.dto.RegisterRequest;
import com.jiangdk.ums.pojo.entity.AppUser;

import javax.mail.MessagingException;
import javax.validation.Valid;

public interface AppUserService extends IService<AppUser>{


    /**
     * 用户名密码登录
      * @param username
     * @param password
     * @return
     */
    AppUser loginByUsername(String username, String password);

    /**
     * 发送验证码
     * @param mobile
     */
    void sendCode(String mobile);

    /**
     * 手机验证码登录
     * @param mobile
     * @param code
     * @return
     */
    AppUser loginByMobile(String mobile,String code);
    /**
     * 发送邮箱验证码
     * @param mail
     */
    String sendCodeByMail(String mail,String code) throws MessagingException;
    /**
     * 生成随机6位验证码
     */
    String generateVerificationCode();

    /**
     * 邮箱验证码登录
     * @param mail
     * @param code
     * @return
     */
    AppUser loginByMail(String mail, String code);

    /**
     * 注册用户
     * @param registerRequest
     */
    void registerUser(@Valid RegisterRequest registerRequest);
}
