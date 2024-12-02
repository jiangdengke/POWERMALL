package com.jiangdk.cms.pojo.vo;

import lombok.Data;

/**
 * @author: JiangDk
 * @date: 2024/12/2 15:40
 * @description: 广告
 */
@Data
public class AdvertVO {
    // 标题
    private String title;
    // 轮播图的地址
    private String img;
    // 点击跳转的地址
    private String redirectUrl;
    // 排序
    private Integer sort;
}
