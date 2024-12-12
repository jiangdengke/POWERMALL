package com.jiangdk.ums.feign;

import com.jiangdk.common.result.Result;
import com.jiangdk.ums.dto.AppUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ums-service")
public interface AppUserFeignClient {

    /**
     * 通过手机号登录
     * @param mobile 手机号
     * @param code 验证码
     * @return
     */
    @GetMapping("/api/ums/loginByMobile")
    Result<AppUserDTO> loginByMobile(@RequestParam("mobile") String mobile,
                             @RequestParam("code") String code);

    /**
     * 通过用户名和密码登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @GetMapping("/api/ums/loginByUsername")
    Result<AppUserDTO> loginByUsername(@RequestParam("username") String username,
                             @RequestParam("password") String password);
}