package com.jiangdk.ums.controller.admin;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiangdk.common.auth.util.StpAdminUtil;
import com.jiangdk.ums.mapper.AdminUserMapper;
import com.jiangdk.ums.pojo.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端/管理员登录
 */
@RestController
@RequestMapping("/api-admin/ums")
public class AdminUserController {
    @Autowired
    private AdminUserMapper adminUserMapper;
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
    public SaResult login(String username,String password){
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.eq("username",username);
        AdminUser adminUser = adminUserMapper.selectOne(adminUserQueryWrapper);
        if (adminUser == null) return SaResult.error("用户不存在").setCode(401);
        if (adminUser.getPassword().equals(password)) {
            StpAdminUtil.login(adminUser.getId());
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
