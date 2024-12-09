package com.jiangdk.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangdk.pms.pojo.entity.Sku;
import com.jiangdk.pms.pojo.vo.CartItemVO;

import java.util.List;

public interface SkuMapper extends BaseMapper<Sku> {
    /**
     * 根据skuId获取商品信息
     * @param skuIds
     * @return
     */
    List<CartItemVO> selectCartItemByIds(List<Long> skuIds);
}