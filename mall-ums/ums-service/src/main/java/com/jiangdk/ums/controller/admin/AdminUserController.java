package com.jiangdk.ums.controller.admin;

import cn.dev33.satoken.util.SaResult;
import com.jiangdk.common.auth.util.StpAdminUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端/管理员登录
 */
@RestController
@RequestMapping("/api-admin/ums")
public class AdminUserController {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
    public SaResult login(String username,String password){
        if ("123456".equals(password)){
            if ("admin".equals(username)) StpAdminUtil.login(1L);
            if ("superadmin".equals(username)) StpAdminUtil.login(2L);
            return SaResult.ok("登录成功").setData(StpAdminUtil.getTokenInfo());
        }else {
            return SaResult.error("密码错误").setCode(401);
        }
    }
    /**
     * 退出
     */
    @GetMapping("/logout")
    public SaResult logout(){
        StpAdminUtil.logout();
        return SaResult.ok("退出系统成功");
    }
}
