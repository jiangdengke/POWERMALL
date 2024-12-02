package com.jiangdk.cms.pojo.form;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class ContentForm implements Serializable {
    private Long id;

    // 分组名称
    @NotBlank(message = "分组名称不能为空")
    private String groupName;

    // 分组编码
    @NotBlank(message = "分组编码不能为空")
    private String groupCode;

    // 内容标题
    @NotBlank(message = "内容标题不能为空")
    private String title;

    // 内容编码
    private String code;

    // 内容排序
    @NotNull(message = "内容排序不能为空")
    private Integer sort;

    // 图片
    private String img;

    // 点击跳转地址
    private String redirectUrl;
}