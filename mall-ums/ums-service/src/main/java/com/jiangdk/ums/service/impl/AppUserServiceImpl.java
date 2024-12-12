package com.jiangdk.ums.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jiangdk.common.exception.BizException;
import com.jiangdk.ums.dto.AppUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.ums.pojo.entity.AppUser;
import com.jiangdk.ums.mapper.AppUserMapper;
import com.jiangdk.ums.service.AppUserService;
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements AppUserService{

    /**
     * 用户名密码登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public AppUserDTO loginByUsername(String username, String password) {
        // 通过用户名查看用户是否存在
        AppUser appUser = this.getOne(new LambdaQueryWrapper<AppUser>().eq(AppUser::getUsername, username));
        if (appUser == null && BCrypt.checkpw(password,appUser.getPassword())){ // 密码校验
            throw new BizException(HttpStatus.HTTP_UNAUTHORIZED,"用户名或密码错误");
        }
        AppUserDTO userDTO = new AppUserDTO();
        BeanUtils.copyProperties(appUser,userDTO);
        return userDTO;
    }
}
