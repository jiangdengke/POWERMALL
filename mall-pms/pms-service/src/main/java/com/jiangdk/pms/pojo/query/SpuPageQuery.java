package com.jiangdk.pms.pojo.query;

import com.jiangdk.common.mybatis.page.PageQuery;
import lombok.Data;

/**
 * @author: JiangDk
 * @date: 2024/11/30 15:44
 * @description:
 */
@Data
public class SpuPageQuery extends PageQuery {
    // 商品名称
    private String name;
    // 商品分类ID
    private Long categoryId;
    // 商品状态
    private Integer status;
}
