package com.jiangdk.pms.service;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.pojo.vo.CartItemVO;

import java.util.List;

/**
 * @author: JiangDk
 * @date: 2024/12/7 19:54
 * @description:
 */
public interface CartService {

    /**
     * 将商品添加到购物车
     * @param skuId 商品库存ID
     * @param count 添加的数量
     */
    void add(Long skuId, Integer count);


    /**
     * 更新购物车中商品的数量
     * @param skuId 商品id
     * @param count 更新后的数量
     */
    void updateCount(Long skuId,Integer count);

    /**
     * 查看购物车
     * @return
     */
    List<CartItemVO> getAll();

    /**
     * 选中/取消选中
     * @param skuId
     * @param checked
     */
    void checked(Long skuId, Boolean checked);

    /**
     * 全选/取消全选
     * @param checked
     */
    void checkedAll(List<Long> skuIds,Boolean checked);


    /**
     * 删除购物车商品
     * @param skuId
     */
    void deleteById(Long skuId);


    /**
     * 清空购物车
     */
    void deleteAll();
}
