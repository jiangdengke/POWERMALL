package com.jiangdk.gateway.controller.app;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.jiangdk.common.result.Result;
import com.jiangdk.ums.dto.AppUserDTO;
import com.jiangdk.ums.feign.AppUserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resources;

/**
 * 应用端/用户
 */
@RestController
@RequestMapping("/api/auth")
public class AppLoginController {
    @Autowired
    private AppUserFeignClient appUserFeignClient;
    /**
     * 通过用户名和密码登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/loginByUsername")
    public Result login(String username,String password){
        Result<AppUserDTO> appUserDTOResult = appUserFeignClient.loginByUsername(username, password);
        System.out.println("appUserDTOResult = " + appUserDTOResult);
        if (appUserDTOResult.isError()){
            return appUserDTOResult;
        }
        AppUserDTO userDTO = appUserDTOResult.getData();
        // 登录
        StpUtil.login(userDTO.getId());
        return Result.success("登录成功",StpUtil.getTokenInfo());
    }

    /**
     * 查看登录状态
     * @return
     */
    @RequestMapping("/isLogin")
    public SaResult isLogin(){
        return SaResult.ok("是否登录："+ StpUtil.isLogin());
    }

    /**
     * 用户退出
     * @return
     */
    @RequestMapping("/logout")
    public SaResult logout(){
        StpUtil.logout();
        return SaResult.ok("退出系统成功");
    }

}
