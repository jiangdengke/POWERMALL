package com.jiangdk.ums.service;

import com.jiangdk.ums.dto.AppUserDTO;
import com.jiangdk.ums.pojo.entity.AppUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AppUserService extends IService<AppUser>{

    /**
     * 用户名密码登录
      * @param username
     * @param password
     * @return
     */
    AppUser loginByUsername(String username, String password);

}
