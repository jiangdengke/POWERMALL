package com.jiangdk.common.mybatis.page;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * 分页公共查询条件
 */
@Data
public class PageQuery {
    // 当前页码
    @NotNull(message = "当前页码不能为空")
    private Long current;

    // 每页显示条数
    @NotNull(message = "每页显示条数不能为空")
    private Long size;
}