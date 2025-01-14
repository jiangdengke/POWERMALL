package com.jiangdk.ums.controller.app;


import com.jiangdk.ums.pojo.dto.RegisterRequest;
import com.jiangdk.ums.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 应用端/用户
 */
@RestController
@RequestMapping("/api/ums")
public class RegisterController {
    @Autowired
    private AppUserService appUserService;
    /**
     * 用户名密码注册
     */
    @PostMapping("/registerByPassword")
    public String registerByPassword
    (
            @Valid @RequestBody RegisterRequest registerRequest
    )
    {
        appUserService.registerUser(registerRequest);
        return "registerByPassword";
    }
}
