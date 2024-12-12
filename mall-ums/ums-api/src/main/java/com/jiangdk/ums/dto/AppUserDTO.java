package com.jiangdk.ums.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUserDTO  {
    private Long id;
    private String username;
    private String mobile;
    private String nickname;
    private String avatar;
}