package com.jiangdk.ums.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpStatus;
import com.alibaba.nacos.api.naming.pojo.healthcheck.impl.Http;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.common.sms.util.AliyunSMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.ums.pojo.entity.AppUser;
import com.jiangdk.ums.mapper.AppUserMapper;
import com.jiangdk.ums.service.AppUserService;

import java.time.Duration;

@Service
@Slf4j
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements AppUserService{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private AliyunSMSUtil aliyunSMSUtil;
    @Value("${aliyun.sms.verification.signName}")
    private String signName;
    @Value("${aliyun.sms.verification.templateCode}")
    private String templateCode;
    /**
     * 用户名密码登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public AppUser loginByUsername(String username, String password) {
        // 通过用户名查看用户是否存在
        AppUser appUser = this.getOne(new LambdaQueryWrapper<AppUser>().eq(AppUser::getUsername, username));
        if (appUser == null || !BCrypt.checkpw(password,appUser.getPassword())){ // 密码校验
            throw new BizException(HttpStatus.HTTP_UNAUTHORIZED,"用户名或密码错误");
        }
        return appUser;
    }

    /**
     * 发送验证码
     *
     * @param mobile
     */
    @Override
    public void sendCode(String mobile) {
        // 生成随机验证码
        String code = RandomUtil.randomNumbers(6);
        log.info("手机号：{}   对应的验证码：{}", mobile, code);
        // 将验证码发送到用户手机
        aliyunSMSUtil.sendVerificationCode(mobile,code,signName,templateCode);
        // 存储验证码到redis
        stringRedisTemplate.opsForValue().set("login:appUser:"+mobile,code, Duration.ofMinutes(5));
    }

    /**
     * 手机验证码登录
     *
     * @param mobile
     * @param code
     * @return
     */
    @Override
    public AppUser loginByMobile(String mobile, String code) {
        // 获取redis中的验证码
        String redisCode = stringRedisTemplate.opsForValue().get("login:appUser:" + mobile);
        if (!redisCode.equals(code) || redisCode == null){
            throw new BizException(HttpStatus.HTTP_NOT_FOUND,"验证码错误或已过期");
        }
        // 通过手机号查用户是否存在
        AppUser appUser = this.getOne(new LambdaQueryWrapper<AppUser>().eq(AppUser::getMobile, mobile));
        if (appUser == null){
            throw new BizException(HttpStatus.HTTP_UNAUTHORIZED,"该手机号未注册");
        }
        return appUser;
    }
}
