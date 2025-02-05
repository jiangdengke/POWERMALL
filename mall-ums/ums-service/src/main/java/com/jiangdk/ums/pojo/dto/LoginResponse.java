package com.jiangdk.ums.pojo.dto;

import cn.dev33.satoken.stp.SaTokenInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private SaTokenInfo saTokenInfo;
    private String username;
    private String avatar;
    private String mail;
}
