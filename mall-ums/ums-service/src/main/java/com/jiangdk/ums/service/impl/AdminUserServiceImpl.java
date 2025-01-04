package com.jiangdk.ums.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiangdk.ums.pojo.entity.AdminUser;
import com.jiangdk.ums.mapper.AdminUserMapper;
import com.jiangdk.ums.service.AdminUserService;
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService{

}
