package com.jiangdk.common.mybatis.page;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private int code; // 状态码
    private String msg; // 响应消息
    private PageData<T> data; // 响应数据

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class PageData<T> {
        private Long total; // 满足分页条件的总记录数
        private List<T> records;// 当前页的记录
    }

    // 分页请求成功
    public static <T> PageResult<T> success(IPage<T> iPage) {
        return new PageResult<T>(HttpStatus.HTTP_OK,"请求成功",
                new PageData<T>(iPage.getTotal(),iPage.getRecords()));
    }
}