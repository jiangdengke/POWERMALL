package com.jiangdk.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiangdk.pms.dto.SkuDTO;
import com.jiangdk.pms.pojo.entity.Sku;
import com.jiangdk.pms.pojo.vo.CartItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuMapper extends BaseMapper<Sku> {
    /**
     * 根据skuId获取商品信息(购物车使用)
     * @param skuIds
     * @return
     */
    List<CartItemVO> selectCartItemByIds(List<Long> skuIds);

    /**
     * 获取商品的信息(预览订单使用)
     * @param skuId
     * @return
     */
    SkuDTO selectSkuById(Long skuId);
    int lockStock(@Param("skuId") Long skuId,@Param("count") Integer count);
}