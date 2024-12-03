package com.jiangdk.search.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: JiangDk
 * @date: 2024/12/3 19:04
 * @description:
 */
@Data
@Document(indexName = "goods")
public class GoodsDoc {
    @Id
    private Long id; //文档ID

    @Field(type = FieldType.Long)
    private Long categoryId;//分类ID

    @Field(type = FieldType.Text, analyzer = "ik_smart", copyTo = "keyword")
    private String name;  //商品名称

    @Field(type = FieldType.Integer)
    private Integer price;//商品价格

    @Field(type = FieldType.Integer)
    private Integer sales;//商品销量

    @Field(type = FieldType.Text, index = false)
    private String img;//图片地址
}
