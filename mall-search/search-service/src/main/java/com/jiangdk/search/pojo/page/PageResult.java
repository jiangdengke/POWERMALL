package com.jiangdk.search.pojo.page;

import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页响应结果
 */
@Data
public class PageResult<T> {

    private Integer code;
    private String msg;
    private PageData<T> data;

    public static <T> PageResult<T> success(List<T> records, Long total) {
        // 封装分页结果
        PageData<T> data = new PageData<>();
        data.setRecords(records);
        data.setTotal(total);

        // 分页的响应结果
        PageResult<T> result = new PageResult<>();
        result.setCode(HttpStatus.HTTP_OK);
        result.setMsg("请求成功");
        result.setData(data);
        return result;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageData<T> {
        // 总记录数
        private Long total;
        // 当前页的数据
        private List<T> records;
    }
}