package com.jiangdk.pms.service;

import com.jiangdk.common.result.Result;
import com.jiangdk.pms.dto.SkuDTO;
import com.jiangdk.pms.dto.StockDTO;
import com.jiangdk.pms.pojo.entity.Sku;
import com.baomidou.mybatisplus.extension.service.IService;
public interface SkuService extends IService<Sku>{
    SkuDTO getSkuById(Long skuId);
    /**
     * 下单减库存
     */
    void lockStock(StockDTO stockDTO);

    /**
     * 取消订单释放库存
     */
    void unlockStock(String orderSn);
}
