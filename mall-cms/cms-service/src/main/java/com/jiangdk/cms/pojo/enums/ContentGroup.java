package com.jiangdk.cms.pojo.enums;

import lombok.Getter;

public enum ContentGroup {

    INDEX_SWIPER("INDEX_SWIPER", "首页轮播图"), 
    INDEX_ADVERT("INDEX_ADVERT", "首页广告"),
    INDEX_NAVIGATION("INDEX_NAVIGATION", "首页导航");

    @Getter
    private String code;

    @Getter
    private String name;

    ContentGroup(String code, String name) {
        this.code = code;
        this.name = name;
    }
}