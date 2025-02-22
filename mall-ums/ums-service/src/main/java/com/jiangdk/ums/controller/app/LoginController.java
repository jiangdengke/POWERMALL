package com.jiangdk.ums.controller.app;


import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.jiangdk.common.result.Result;
import com.jiangdk.ums.pojo.dto.LoginResponse;
import com.jiangdk.ums.pojo.entity.AppUser;
import com.jiangdk.ums.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

/**
 * 应用端/用户
 */
@RestController
@RequestMapping("/api/ums")
public class LoginController {
    @Autowired
    private AppUserService appUserService;

    /**
     * 邮箱验证码登录
     */
    @PostMapping("/loginByMail")
    public Result<LoginResponse> loginByMail(
            @RequestParam("mail") String mail,
            @RequestParam("code") String code){
        AppUser appUser = appUserService.loginByMail(mail, code);
        // 登录系统
        StpUtil.login(appUser.getId());

        // 构建响应数据
        LoginResponse response = new LoginResponse();
        response.setSaTokenInfo(StpUtil.getTokenInfo());
        response.setUsername(appUser.getUsername());
        response.setAvatar(appUser.getAvatar());
        response.setMail(appUser.getMail());
        return Result.success(response);
    }

    /**
     * 获取邮箱验证码
     * @param mail
     * @return
     */
    @GetMapping("/getCodeByMail")
    public Result sendCodeByMail(String mail) throws MessagingException {
        String code = appUserService.sendCodeByMail(mail, appUserService.generateVerificationCode());
        return Result.success(code);
    }
    /**
     * 手机验证码登录
     * @param mobile 手机号
     * @param code 验证码
     * @return
     */
    @PostMapping("/loginByMobile")
    Result<SaTokenInfo> loginByMobile(
            @RequestParam("mobile") String mobile,
            @RequestParam("code") String code){
        AppUser appUser = appUserService.loginByMobile(mobile, code);
        // 登录系统
        StpUtil.login(appUser.getId());
        return Result.success();
    }

    /**
     * 获取手机验证码
     * @param mobile
     * @return
     */
    @GetMapping("/loginCode")
    public Result sendCode(String mobile){
        appUserService.sendCode(mobile);
        return Result.success();
    }
    /**
     * 用户名和密码登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/loginByUsername")
    Result<LoginResponse> loginByUsername(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ){
        AppUser appUser = appUserService.loginByUsername(username, password);
        // 登录系统
        StpUtil.login(appUser.getId());

        // 构建响应数据
        LoginResponse response = new LoginResponse();
        response.setSaTokenInfo(StpUtil.getTokenInfo());
        response.setUsername(appUser.getUsername());
        response.setAvatar(appUser.getAvatar());
        response.setMail(appUser.getMail());
//        return Result.success("登录成功",StpUtil.getTokenInfo());
        return Result.success(response);
    }
    /**
     * 用户退出
     * @return
     */
    @DeleteMapping("/logout")
    public SaResult logout(){
        StpUtil.logout();
        return SaResult.ok("退出系统成功");
    }
}
