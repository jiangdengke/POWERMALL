package com.jiangdk.cms.pojo.query;

import com.jiangdk.common.mybatis.page.PageQuery;
import lombok.Data;

@Data
public class ContentPageQuery extends PageQuery {
    // 分组编码
    private String groupCode;
    // 内容标题
    private String title;
}