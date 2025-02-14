package com.jiangdk.gateway.component;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/12/11 20:39
 * @description:
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    /**
     * 获取权限列表
     * @param o
     * @param s
     * @return
     */
    @Override
    public List<String> getPermissionList(Object o, String s) {
        return Collections.emptyList();
    }

    /**
     * 获取角色列表
     * @param o
     * @param s
     * @return
     */

    @Override
    public List<String> getRoleList(Object o, String s) {
        return Collections.emptyList();
    }
}
