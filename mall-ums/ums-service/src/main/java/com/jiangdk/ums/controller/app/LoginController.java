package com.jiangdk.ums.controller.app;


import com.jiangdk.common.result.Result;
import com.jiangdk.ums.dto.AppUserDTO;
import com.jiangdk.ums.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ums")
public class LoginController {
    @Autowired
    private AppUserService appUserService;
    /**
     * 通过手机号登录
     * @param mobile 手机号
     * @param code 验证码
     * @return
     */
    @GetMapping("/loginByMobile")
    Result<AppUserDTO> loginByMobile(@RequestParam("mobile") String mobile,
                                     @RequestParam("code") String code){

        return Result.success();
    }

    /**
     * 通过用户名和密码登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @GetMapping("/loginByUsername")
    Result<AppUserDTO> loginByUsername(@RequestParam("username") String username,
                                       @RequestParam("password") String password){
        AppUserDTO userDTO = appUserService.loginByUsername(username, password);
        return Result.success(userDTO);
    }
}
