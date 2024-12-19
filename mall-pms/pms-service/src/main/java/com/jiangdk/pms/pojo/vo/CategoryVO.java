package com.jiangdk.pms.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {
    private Long id;
    private Long parentId;
    private String name;
    private Integer level;
    private String icon;
    private Double weight;
    private List<CategoryVO> children;
}